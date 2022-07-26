package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieTMDBApi {
    @GET("3/movie/now_playing?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&page=1&region=BR")
    fun getNovidades() : Call<ListMovieDto>

    @GET("3/movie/{id}/watch/providers?api_key=7ba0016d8a29404b95fd34bb3130e470")
    fun getWatchProviders(@Path("id") id : Int) : Call<WatchProviderListDto>


    @GET("3/movie/{id}?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getMovieDetails(@Path("id")id : Int) : Call<MovieDetailsDto>

    @GET("3/movie/{id}/recommendations?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&page=1")
    fun getRecomendacoes(@Path("id") id : Int) : Call<ListMovieDto>

    @GET("3/movie/{id}/credits?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getCredits(@Path("id")id : Int) : Call<CreditsDto>

    @GET("3/discover/movie?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    fun getDiscover(listGenre : List<Genre>) : Call<ListMovieDto>
}