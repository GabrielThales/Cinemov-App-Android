package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Series.SerieDetailsDto

interface SerieDetailsServiceListener{
    fun requisicaoTerminouSeriDetails(t : SerieDetailsDto?)
    fun falhaRequisicaoSerieDetails(t : String?)
}