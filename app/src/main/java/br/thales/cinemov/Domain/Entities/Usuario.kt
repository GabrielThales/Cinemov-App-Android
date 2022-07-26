package br.thales.cinemov.Domain.Entities

import android.os.Parcelable
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import kotlinx.parcelize.Parcelize

@Parcelize
class Usuario(var id : String? = null, var dataNascimento : String? = null, var nome : String? = null, var fotoUri : String? = null, var filmesFavoritos: List<MovieDto>? = null, var descricao : String? = null, var filmesAvaliados : Int? = null, var listFav : List<MovieDto>? = null  ) : Parcelable {

}