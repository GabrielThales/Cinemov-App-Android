package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Movie.CreditsDto

interface CreditsListener {

    fun reqCreditsTerminou(credits : CreditsDto)
    fun falhaRequisicao(t : String?)
}