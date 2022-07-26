package br.thales.cinemov.Application.Services

import br.thales.cinemov.Application.Dtos.People.PeopleDetailsDto
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Interfaces.MovieTMDBApi
import br.thales.cinemov.Application.Interfaces.PeopleServiceListener
import br.thales.cinemov.Application.Interfaces.PeopleTMDBApi
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PessoaService {

    private var api : PeopleTMDBApi
    private lateinit var listener : PeopleServiceListener

    companion object{
        val instance = GeneroService()
    }


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PeopleTMDBApi::class.java)
    }

    fun setEventListener(eventListener : PeopleServiceListener){
        listener = eventListener
    }

    fun getPeopleDetails(id : Int){
        val call = api.getPeopleDetails(id)

        runBlocking {
            call.enqueue(object : Callback<PeopleDetailsDto>{
                override fun onResponse(
                    call: Call<PeopleDetailsDto>,
                    response: Response<PeopleDetailsDto>
                ) {
                    listener.requisicaoTerminou(response.body())
                }

                override fun onFailure(call: Call<PeopleDetailsDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message.toString())
                }

            })
        }

    }
}

