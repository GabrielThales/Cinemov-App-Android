package br.thales.cinemov.Application.Dtos.Series

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
) : Parcelable