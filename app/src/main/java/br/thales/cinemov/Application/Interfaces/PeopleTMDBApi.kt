package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Application.Dtos.People.PeopleDetailsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleTMDBApi {
    @GET("https://api.themoviedb.org/3/person/{person_id}?api_key=7ba0016d8a29404b95fd34bb3130e470&language=pt-BR")
    fun getPeopleDetails(@Path("person_id") id : Int) : Call<PeopleDetailsDto>

}