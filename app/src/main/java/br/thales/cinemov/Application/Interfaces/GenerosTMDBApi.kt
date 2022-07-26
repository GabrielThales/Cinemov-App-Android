package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GenerosTMDBApi {
    @GET("/3/genre/movie/list?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getGenerosFilmes() : Call<GenerosDto>

    @GET("/3/genre/tv/list?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-Br")
    fun getGenerosSerie() : Call<GenerosDto>

}