package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.CreditsDto
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDetailsDto
import br.thales.cinemov.Application.Dtos.Movie.WatchProviderListDto
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Dtos.Series.SerieDetailsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SerieTMDBApi {
    @GET("3/tv/now_playing?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&page=1&region=BR")
    fun getNovidades() : Call<ListSeriesDto>

    @GET("3/tv/{id}/watch/providers?api_key=7ba0016d8a29404b95fd34bb3130e470")
    fun getWatchProviders(@Path("id") id : Int) : Call<WatchProviderListDto>


    @GET("3/tv/{id}?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getSerieDetails(@Path("id")id : Int) : Call<SerieDetailsDto>

    @GET("3/tv/{id}/recommendations?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&page=1")
    fun getRecomendacoes(@Path("id") id : Int) : Call<ListSeriesDto>

    @GET("3/tv/{id}/credits?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getCredits(@Path("id")id : Int) : Call<CreditsDto>

    @GET("3/discover/tv?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    fun getDiscover(listGenre : List<Genre>) : Call<ListSeriesDto>
}