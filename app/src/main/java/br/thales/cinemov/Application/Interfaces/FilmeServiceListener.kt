package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Movie.*

interface FilmeServiceListener : RetrofitServicesListener<ListMovieDto> {
    fun movieDetailsReq(movieDetailsDto: MovieDetailsDto)
    fun watchProvidersReq(listProviders : List<Provider>)
    fun creditsReq (credits : CreditsDto)
}