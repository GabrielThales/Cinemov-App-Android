package br.thales.cinemov.Application.Dtos.People

data class ListPeopleDto(
        val page: Int?,
        val results: List<PeopleResult>?,
        val total_pages: Int?,
        val total_results: Int?
)