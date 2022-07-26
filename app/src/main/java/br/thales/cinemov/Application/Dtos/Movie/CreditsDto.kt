package br.thales.cinemov.Application.Dtos.Movie

data class CreditsDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)