package br.thales.cinemov

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.*
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.presentation.RecyclerViews.ListMovieAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import br.thales.cinemov.presentation.RecyclerViews.WatchProviderAdapter
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class MovieDetailsFragmentViewModel : ViewModel(), FilmeServiceListener, RecyclerViewItemListener {
    lateinit var movie : MovieDto
    lateinit var movieDetails : MovieDetailsDto
    val filmeService = FilmeService()
    val listMovieAdapter  = ListMovieAdapter()
    val watchProviderAdapter = WatchProviderAdapter()

    init {
        filmeService.setEventListener(this)
        watchProviderAdapter.setRecyclerViewItemListener(this)
        //listMovieAdapter.setRecyclerViewItemListener(this)
    }

    override fun movieDetailsReq(movieDetailsDto: MovieDetailsDto) {
        this.movieDetails = movieDetailsDto
        Log.i("MovieDetails", movieDetailsDto.title)
    }

    override fun watchProvidersReq(listProviders: List<Provider>) {
        watchProviderAdapter.listProviders = listProviders
        watchProviderAdapter.notifyDataSetChanged()
        //Log.i("WatchProviders", listProviders[0].provider_name.toString())
    }

    override fun creditsReq(credits: CreditsDto) {
        TODO("Not yet implemented")
    }

    override fun requisicaoTerminou(t: ListMovieDto?) {
        if (t != null){
            listMovieAdapter.listMovieDto = t.results
            listMovieAdapter.notifyDataSetChanged()
        }

    }

    override fun falhaRequisicao(t: String?) {
        Log.e("MovieDetails", t.toString())
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        if(view.id == R.id.rcvWatchProviders){

        }
    }


}