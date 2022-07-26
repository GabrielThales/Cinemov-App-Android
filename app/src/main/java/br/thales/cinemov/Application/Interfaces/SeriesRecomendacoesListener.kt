package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto

interface SeriesRecomendacoesListener {
    fun requisicaoSerieRecomendacoesTerminou( t : ListSeriesDto?)
    fun falhaSerieRecomendacoes(t : String)
}