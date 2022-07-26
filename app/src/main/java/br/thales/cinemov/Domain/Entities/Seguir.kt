package br.thales.cinemov.Domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Seguir(var userId : String? = null, var seguidores : MutableList<Seguidor>? = null, var seguindo : MutableList<Seguidor>? = null) : Parcelable {

}