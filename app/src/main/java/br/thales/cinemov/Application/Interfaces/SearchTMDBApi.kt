package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.People.ListPeopleDto
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchTMDBApi {

    @GET("search/movie?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&include_adult=false")
    fun pesquisarFilme(@Query("query")pesquisa : String, @Query("page") page : Int) : Call<ListMovieDto>

    @GET("search/person?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&include_adult=false")
    fun pesquisarPessoa(@Query("query")pesquisa : String, @Query("page") page : Int) : Call<ListPeopleDto>

    @GET("search/tv?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&page=1&include_adult=false")
    fun pesquisarSerie(@Query("query")pesquisa : String, @Query("page") page : Int) : Call<ListSeriesDto>

}