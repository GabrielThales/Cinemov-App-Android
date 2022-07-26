package br.thales.cinemov

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.databinding.FilmeDetailsFragmentBinding
import br.thales.cinemov.databinding.HomeFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.GeneroAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class MovieDetailsFragment : Fragment(), RecyclerViewItemListener {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsFragmentViewModel
    private var _binding: FilmeDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val generoAdapter = GeneroAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilmeDetailsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailsFragmentViewModel::class.java)
        if (arguments != null){

            viewModel.movie = requireArguments().getParcelable<MovieDto>("movie")!!
            Toast.makeText(context, viewModel.movie.title, Toast.LENGTH_LONG).show()

            viewModel.filmeService.getWatchProviders(viewModel.movie.id!!)
            viewModel.filmeService.getRecomendacoes(viewModel.movie.id!!)


            //preenchendo campos

            binding.txtTituloFilme.text = viewModel.movie.title
            binding.txtDataExibicao.text = viewModel.movie.release_date
            //binding.txtDuracaoFilme.text = viewModel.movieDetails.runtime.toString()
            binding.txtOverview.text = viewModel.movie.overview

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + viewModel.movie.poster_path).into(binding.imgPosterFilme)

            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL

            val layoutManager2 = LinearLayoutManager(context)
            layoutManager2  .orientation = LinearLayoutManager.HORIZONTAL



            binding.rcvWatchProviders.adapter = viewModel.watchProviderAdapter
            binding.rcvWatchProviders.layoutManager = layoutManager

            binding.rcvRecomendacoes.adapter = viewModel.listMovieAdapter
            viewModel.listMovieAdapter.setRecyclerViewItemListener(this)
            binding.rcvRecomendacoes.layoutManager = layoutManager2

        }
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        Log.i("ItemListener", "funcionando: ${id} / ${viewModel.listMovieAdapter.listMovieDto.size}")
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movie", viewModel.listMovieAdapter.listMovieDto[id])
        startActivity(intent)
    }


}