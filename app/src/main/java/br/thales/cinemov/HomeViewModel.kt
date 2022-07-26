package br.thales.cinemov

import android.util.Log
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.CreditsDto
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDetailsDto
import br.thales.cinemov.Application.Dtos.Movie.Provider
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import br.thales.cinemov.presentation.RecyclerViews.MovieAdapter

class HomeViewModel : ViewModel(), FilmeServiceListener{

    var movieAdapter = MovieAdapter()
    val movieService = FilmeService()

    init {
        movieService.setEventListener(this)
        movieService.getNovidades()
    }

    override fun movieDetailsReq(movieDetailsDto: MovieDetailsDto) {
        //nada
    }

    override fun watchProvidersReq(listProviders: List<Provider>) {
        TODO("Not yet implemented")
    }

    override fun creditsReq(credits: CreditsDto) {
        TODO("Not yet implemented")
    }


    override fun requisicaoTerminou(t: ListMovieDto?) {
        if (t != null) {
            movieAdapter.listMovieDto = t.results
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun falhaRequisicao(t: String?) {
        Log.e("RetrofitTMDB", t.toString())
    }
}