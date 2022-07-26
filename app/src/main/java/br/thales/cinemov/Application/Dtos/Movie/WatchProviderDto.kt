package br.thales.cinemov.Application.Dtos.Movie

data class WatchProviderDto(
    val buy: List<Provider>,
    val flatrate: List<Provider>,
    val link: String,
    val rent: List<Provider>
)