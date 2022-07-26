package br.thales.cinemov.Application.Interfaces

interface RetrofitServicesListener<T>{

    fun requisicaoTerminou(t : T?)
    fun falhaRequisicao(t : String?)

}