package br.thales.cinemov.Domain.Entities

import android.os.Parcelable
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avaliacao(var id : String? = null,var autorId : String? = null, var nomeAutor : String? = null ,var authorPhoto : String? = null, var movieDto : MovieDto? = null, var titulo : String? = null, var avaliacaoTexto : String? = null, var notaFilme : Double? = null, var recomenda : Boolean? = null, var data : String? = null, var serieDto : SerieDto? = null) : Parcelable {
}