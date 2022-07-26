package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.People.ListPeopleDto

interface SearchPeopleServiceEventListener {

    fun requisicaoTerminou(t : ListPeopleDto?)
    fun falhaRequisicao(t : String?)
}