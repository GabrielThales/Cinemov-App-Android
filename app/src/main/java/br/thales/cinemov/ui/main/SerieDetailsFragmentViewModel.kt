package br.thales.cinemov.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import br.thales.cinemov.Application.Dtos.Series.CreatedBy
import br.thales.cinemov.Application.Dtos.Series.SerieDetailsDto
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.RetrofitServicesListener
import br.thales.cinemov.Application.Interfaces.SerieDetailsServiceListener
import br.thales.cinemov.Application.Services.GeneroService
import br.thales.cinemov.Application.Services.SerieService
import br.thales.cinemov.SeriesDetailsActivityViewModel
import br.thales.cinemov.presentation.RecyclerViews.*

class SerieDetailsFragmentViewModel : ViewModel() {

//    val generoAdapter : GeneroAdapter = GeneroAdapter()
    val serieService = SerieService()
    val generoAdapterSeries : GeneroAdapter = GeneroAdapter()
    val watchProvidersAdapter : WatchProviderAdapter = WatchProviderAdapter()
    val createdByAdapter : CreatedByAdapter = CreatedByAdapter()
    val serieAdapter : SeriesAdapter = SeriesAdapter()
    val seasonAdapter : SeasonAdapter = SeasonAdapter()

    init {


    }
 /*   override fun requisicaoTerminou(t: GenerosDto?) {
        count++
        if (count == 1 && t != null){
            generosFilme = t
            //generoAdapter.listGenre = t.genres

        } else if (count == 2 && t != null){
            generosSerie = t
            generoAdapterSeries.listGenre = t.genres
        }

    }*/




 /*   override fun falhaRequisicao(t: String?) {
        Log.e("Generos", "Falha")
    }*/

}