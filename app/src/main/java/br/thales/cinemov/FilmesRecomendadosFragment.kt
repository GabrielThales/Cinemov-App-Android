package br.thales.cinemov

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.*
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Infra.Interfaces.ListAvaliacoesEventListener
import br.thales.cinemov.databinding.FilmeDetailsFragmentBinding
import br.thales.cinemov.databinding.FilmesRecomendadosFragmentBinding
import br.thales.cinemov.databinding.RecomendacoesFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.firebase.auth.FirebaseAuth

class FilmesRecomendadosFragment : Fragment(), ListAvaliacoesEventListener, FilmeServiceListener, RecyclerViewItemListener, View.OnDragListener {

    companion object {
        fun newInstance() = FilmesRecomendadosFragment()
    }

    private lateinit var viewModel: FilmesRecomendadosViewModel
    private var _binding: FilmesRecomendadosFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var minhasAvaliacoes : List<Avaliacao>
    private lateinit var recomendacoes : ListMovieDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilmesRecomendadosFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmesRecomendadosViewModel::class.java)
        viewModel.avaliacaoRepository.setListEventListener(this)
        viewModel.filmeService.setEventListener(this)

        var swipe = binding.swipeContainer

        swipe.setOnRefreshListener {
            viewModel.avaliacaoRepository.getMinhasAvaliacoes(FirebaseAuth.getInstance().currentUser!!.uid)
            swipe.isRefreshing = false;
        }

//        binding.btnAttRecomendacoes.setOnClickListener {
//            viewModel.filmeService.getRecomendacoes(minhasAvaliacoes.first{ f -> f.notaFilme!! >= 7  }.movieDto!!.id!!)
//        }

    }

    override fun requisicaoFirebaseTerminou(t: List<Avaliacao>?) {
        if (t.isNullOrEmpty()){
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Nenhuma Avaliação :(")
            alertDialog.setMessage("Para fazer melhores recomendações de filmes precisamos que você avalie pelo menos 1 filme, pense em algum filme favorito seu, quanto mais avaliações melhores ficam as recomendações, vá para a aba de pesquisa e avalie seus filmes favoritos")
            alertDialog.setPositiveButton("Ok", null)
            alertDialog.create()
            alertDialog.show()
        } else {
            viewModel.filmeService.getRecomendacoes(t.last{ f -> f.notaFilme!! >= 7  }.movieDto!!.id!!)

            minhasAvaliacoes = t
            Toast.makeText(requireContext(), t.last{ f -> f.notaFilme!! >= 7  }.movieDto!!.title!!, Toast.LENGTH_LONG).show()

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL


            viewModel.movieAdapter.setRecyclerViewItemListener(this)

            binding.rcvRecomendacoesFilmes.adapter = viewModel.movieAdapter
            binding.rcvRecomendacoesFilmes.layoutManager = layoutManager

            binding.rcvRecomendacoesFilmes.setOnDragListener(this)

        }
    }

    override fun falhaRequisicaoFirebase(t: String?) {
        Toast.makeText(requireContext(), "Aconteceu Algum Problema...", Toast.LENGTH_SHORT).show()
    }

    override fun movieDetailsReq(movieDetailsDto: MovieDetailsDto) {
        TODO("Not yet implemented")
    }

    override fun watchProvidersReq(listProviders: List<Provider>) {
        TODO("Not yet implemented")
    }

    override fun creditsReq(credits: CreditsDto) {
        TODO("Not yet implemented")
    }

    override fun requisicaoTerminou(t: ListMovieDto?) {
        if(t != null && !t.results.isNullOrEmpty()){
            recomendacoes = t
            viewModel.movieAdapter.listMovieDto = t.results
            viewModel.movieAdapter.notifyDataSetChanged()
        }
    }

    override fun falhaRequisicao(t: String?) {
        Toast.makeText(requireContext(), "Aconteceu algum Problema", Toast.LENGTH_SHORT).show()
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movie", viewModel.movieAdapter.listMovieDto[id])
        startActivity(intent)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        viewModel.avaliacaoRepository.getMinhasAvaliacoes(FirebaseAuth.getInstance().currentUser!!.uid)
        return true
    }

}