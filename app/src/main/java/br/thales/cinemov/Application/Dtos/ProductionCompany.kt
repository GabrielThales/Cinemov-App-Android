package br.thales.cinemov.Application.Dtos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
) : Parcelable