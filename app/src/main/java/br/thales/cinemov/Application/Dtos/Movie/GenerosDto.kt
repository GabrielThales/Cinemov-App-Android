package br.thales.cinemov.Application.Dtos.Movie

import android.os.Parcelable
import br.thales.cinemov.Application.Dtos.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerosDto(
    val genres: List<Genre>
) : Parcelable