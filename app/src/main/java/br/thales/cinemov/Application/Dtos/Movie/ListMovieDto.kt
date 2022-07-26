package br.thales.cinemov.Application.Dtos.Movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMovieDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable