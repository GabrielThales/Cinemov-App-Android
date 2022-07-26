package br.thales.cinemov.Application.Dtos.People

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleResult(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val known_for: List<KnownFor>?,
    val known_for_department: String?,
    val name: String?,
    val popularity: Double?,
    val profile_path: String?
) : Parcelable