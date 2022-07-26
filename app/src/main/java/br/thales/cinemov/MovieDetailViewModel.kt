package br.thales.cinemov

import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener

class MovieDetailViewModel : ViewModel() {
    companion object{
        var movie : MovieDto? = null
    }
}