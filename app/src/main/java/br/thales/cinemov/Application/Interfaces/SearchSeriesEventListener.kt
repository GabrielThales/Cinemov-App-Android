package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.People.ListPeopleDto
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto

interface SearchSeriesEventListener {

    fun requisicaoTerminouSeries(t : ListSeriesDto?)
    fun falhaRequisicaoSeries(t : String?)
}