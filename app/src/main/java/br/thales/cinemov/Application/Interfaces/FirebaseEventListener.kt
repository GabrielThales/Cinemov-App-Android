package br.thales.cinemov.Application.Interfaces

interface FirebaseEventListener<T> {

    fun requisicaoFirebaseTerminou(t : T?)
    fun falhaRequisicaoFirebase(t : String?)

}