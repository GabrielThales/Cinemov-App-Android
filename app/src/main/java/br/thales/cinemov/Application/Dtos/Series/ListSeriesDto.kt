package br.thales.cinemov.Application.Dtos.Series

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListSeriesDto(
    val page: Int,
    val results: List<SerieDto>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable