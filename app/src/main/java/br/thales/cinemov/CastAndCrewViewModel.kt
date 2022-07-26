package br.thales.cinemov

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.CreditsDto
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDetailsDto
import br.thales.cinemov.Application.Dtos.Movie.Provider
import br.thales.cinemov.Application.Interfaces.CreditsListener
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.Application.Services.SerieService
import br.thales.cinemov.presentation.RecyclerViews.CastAdapter
import br.thales.cinemov.presentation.RecyclerViews.CreatedByAdapter
import br.thales.cinemov.presentation.RecyclerViews.CrewAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener

class CastAndCrewViewModel : ViewModel(), FilmeServiceListener, RecyclerViewItemListener, CreditsListener {

    val castAdapter = CastAdapter()
    val crewAdapter = CrewAdapter()
    val filmeSerivce = FilmeService()
    val serieService = SerieService()

    init {
        filmeSerivce.setEventListener(this)
        serieService.setEventListenerCredits(this)
        castAdapter.setRecyclerViewItemListener(this)
        crewAdapter.setRecyclerViewItemListener(this)
    }

    override fun movieDetailsReq(movieDetailsDto: MovieDetailsDto) {
        TODO("Not yet implemented")
    }

    override fun watchProvidersReq(listProviders: List<Provider>) {
        TODO("Not yet implemented")
    }

    override fun creditsReq(credits: CreditsDto) {
        castAdapter.listCast = credits.cast
        castAdapter.notifyDataSetChanged()

        crewAdapter.listCrew = credits.crew
        crewAdapter.notifyDataSetChanged()
        Log.i("Retrofit:", "Credits ok")
    }

    override fun requisicaoTerminou(t: ListMovieDto?) {
        TODO("Not yet implemented")
    }

    override fun reqCreditsTerminou(credits: CreditsDto) {
        castAdapter.listCast = credits.cast
        castAdapter.notifyDataSetChanged()

        crewAdapter.listCrew = credits.crew
        crewAdapter.notifyDataSetChanged()
        Log.i("Retrofit:", "Credits ok")
    }

    override fun falhaRequisicao(t: String?) {
        Log.e("Retrofit", t.toString())
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {

    }
}