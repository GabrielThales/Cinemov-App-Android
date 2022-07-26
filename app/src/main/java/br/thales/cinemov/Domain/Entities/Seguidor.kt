package br.thales.cinemov.Domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Seguidor(var id : String? = null, var nome : String? = null, var dataSeguidor : String? = null) : Parcelable {
}