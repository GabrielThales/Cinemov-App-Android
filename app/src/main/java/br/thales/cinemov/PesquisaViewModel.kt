package br.thales.cinemov

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Interfaces.SearchServiceEventListener
import br.thales.cinemov.Application.Services.PesquisaService
import br.thales.cinemov.presentation.RecyclerViews.ListMovieAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import br.thales.cinemov.presentation.RecyclerViews.MovieAdapter
import br.thales.cinemov.presentation.RecyclerViews.PeopleAdapter
import br.thales.cinemov.presentation.RecyclerViews.SeriesAdapter

class PesquisaViewModel : ViewModel() {

    val listMovieAdapter = ListMovieAdapter()
    val pesquisaService = PesquisaService()
    val listPeopleAdapter = PeopleAdapter()
    val listSeriesAdapter = SeriesAdapter()
    var totalPages : Int = 0
    var totalResult : Int = 0
    companion object{
        var listFav = mutableListOf<MovieDto>()
    }


    init {
//        pesquisaService.setEventListener(this)
    }





}