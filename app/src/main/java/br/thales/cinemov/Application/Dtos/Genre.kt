package br.thales.cinemov.Application.Dtos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Int? = null,
    val name: String? = null
) : Parcelable