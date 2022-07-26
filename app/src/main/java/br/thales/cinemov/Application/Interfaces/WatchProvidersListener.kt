package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Movie.Provider

interface WatchProvidersListener {
    fun watchProvidersReq(listProviders : List<Provider>)
    fun falhaRequisicaoWp(t : String?)
}