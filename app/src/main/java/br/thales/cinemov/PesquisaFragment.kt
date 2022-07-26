package br.thales.cinemov

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.People.ListPeopleDto
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Interfaces.SearchPeopleServiceEventListener
import br.thales.cinemov.Application.Interfaces.SearchSeriesEventListener
import br.thales.cinemov.Application.Interfaces.SearchServiceEventListener
import br.thales.cinemov.databinding.PesquisaFragmentBinding
import br.thales.cinemov.databinding.UsuarioFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.android.material.snackbar.Snackbar

class PesquisaFragment : Fragment(), RecyclerViewItemListener, android.widget.SearchView.OnQueryTextListener,
    SearchServiceEventListener, SearchPeopleServiceEventListener, SearchSeriesEventListener {
    var page = 1

    companion object {
        fun newInstance() = PesquisaFragment()
    }

    private lateinit var viewModel: PesquisaViewModel
    var pesquisaFav = false
    private var _binding: PesquisaFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PesquisaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PesquisaViewModel::class.java)
        viewModel.pesquisaService.setEventListener(this)
        viewModel.pesquisaService.setPeopleEventListener(this)
        viewModel.pesquisaService.setSeriesEventListener(this)



        val pesquisa = binding.searchView.query

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item, arrayListOf("Filme", "Pessoa","Series"))
        binding.spnTipoPesquisa.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
        viewModel.listMovieAdapter.setRecyclerViewItemListener(this)
        viewModel.listPeopleAdapter.setRecyclerViewItemListener(this)
        viewModel.listSeriesAdapter.setRecyclerViewItemListener(this)


    }

    override fun recyclerViewItemClicked(view: View, id: Int) {

        if (view.id == R.id.rcvpeople){
            //Toast.makeText(requireContext(), viewModel.listPeopleAdapter.listPeople[id].name, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, PeopleDetailsActivity::class.java)
            intent.putExtra("people", viewModel.listPeopleAdapter.listPeople[id])
            startActivity(intent)
        } else if (view.id == R.id.movieRcv2){

            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", viewModel.listMovieAdapter.listMovieDto[id])
            if (pesquisaFav){
                intent.putExtra("pesquisaFav", true)
            }
            startActivity(intent)

        } else {
            val intent = Intent(context, SeriesDetailsActivity::class.java)
            intent.putExtra("serie", viewModel.listSeriesAdapter.listSeries[id])
            if (pesquisaFav){
                intent.putExtra("pesquisaFav", true)
            }
            startActivity(intent)
        }



    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val gridLayout = GridLayoutManager(requireContext(), 3)
        if (query?.isEmpty() == true){
            //Snackbar.make(this.requireView(), "Digite na barra de pesquisa", 3000)
            Toast.makeText(requireContext(), "Preencha o campo de pesquisa", Toast.LENGTH_SHORT).show()
        } else if(binding.spnTipoPesquisa.selectedItem.toString() == "Filme") {

                //val gridLayout = GridLayoutManager(requireContext(), 3)
                viewModel.pesquisaService.getBuscaFilme(query.toString(), page)
                binding.rcvResultado.adapter = viewModel.listMovieAdapter
                binding.rcvResultado.layoutManager = gridLayout


        } else if (binding.spnTipoPesquisa.selectedItem.toString() == "Pessoa"){

            //val gridLayout = GridLayoutManager(requireContext(), 3)
            viewModel.pesquisaService.getBuscarPessoas(query.toString(), page)
            binding.rcvResultado.adapter = viewModel.listPeopleAdapter
            binding.rcvResultado.layoutManager = gridLayout

        } else if (binding.spnTipoPesquisa.selectedItem.toString() == "Series"){

            viewModel.pesquisaService.getBuscarSeries(query.toString(), page)
            //val gridLayout = GridLayoutManager(requireContext(), 3)
            binding.rcvResultado.adapter = viewModel.listSeriesAdapter
            binding.rcvResultado.layoutManager = gridLayout

        }

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun requisicaoTerminou(t: ListMovieDto?) {
        if (t != null && t.total_results != 0){
            viewModel.listMovieAdapter.listMovieDto = t.results
            viewModel.totalPages = t.total_pages
            viewModel.totalResult = t.total_results

            Toast.makeText(requireContext(), "${viewModel.totalResult} Resultados!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Nada encontrado :(", Toast.LENGTH_SHORT).show()
        }
    }

    override fun requisicaoTerminou(t: ListPeopleDto?) {
        if(t != null && t.total_results != 0 && !t.results.isNullOrEmpty()){
            viewModel.listPeopleAdapter.listPeople = t.results

            Toast.makeText(requireContext(), "${viewModel.totalResult} Resultados!", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(requireContext(), "Nada encontrado :(", Toast.LENGTH_SHORT).show()
        }
    }

    override fun falhaRequisicao(t: String?) {
        Log.e("FirebaseReq", t.toString())
        Snackbar.make(requireView(),"Falha na Pesquisa",2000)
    }

    override fun requisicaoTerminouSeries(t: ListSeriesDto?) {
        if (t != null && t.total_results != 0 && !t.results.isNullOrEmpty()){
            viewModel.listSeriesAdapter.listSeries = t.results
            viewModel.totalPages = t.total_pages
            viewModel.totalResult = t.total_results
        } else {
            Toast.makeText(requireContext(), "Nada encontrado :(", Toast.LENGTH_SHORT).show()
        }

    }

    override fun falhaRequisicaoSeries(t: String?) {
        Snackbar.make(requireView(),"Falha na Pesquisa",2000)
    }

}

