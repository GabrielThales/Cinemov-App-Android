package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.People.ListPeopleDto

interface SearchServiceEventListener : RetrofitServicesListener<ListMovieDto> {


}