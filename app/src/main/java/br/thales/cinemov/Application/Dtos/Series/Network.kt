package br.thales.cinemov.Application.Dtos.Series

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
) : Parcelable