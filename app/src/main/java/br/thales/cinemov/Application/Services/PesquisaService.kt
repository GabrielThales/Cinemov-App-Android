package br.thales.cinemov.Application.Services

import android.util.Log
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.People.ListPeopleDto
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Interfaces.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PesquisaService {
    private var api : SearchTMDBApi
    private lateinit var listener : SearchServiceEventListener
    private lateinit var peopleListener : SearchPeopleServiceEventListener
    private lateinit var seriesListener : SearchSeriesEventListener

    companion object{
        val instance = PesquisaService()
    }


    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(SearchTMDBApi::class.java)
    }


    fun setEventListener(eventListener : SearchServiceEventListener){
        listener = eventListener
    }

    fun setPeopleEventListener(eventListener : SearchPeopleServiceEventListener){
        peopleListener = eventListener
    }

    fun setSeriesEventListener(eventListener: SearchSeriesEventListener){
        seriesListener = eventListener
    }

    fun getBuscaFilme(pesquisa : String, page : Int){
        val call = api.pesquisarFilme(pesquisa, page)
        runBlocking {
            call.enqueue(object : Callback<ListMovieDto> {
                override fun onResponse(
                        call: Call<ListMovieDto>,
                        response: Response<ListMovieDto>
                ) {
                    listener.requisicaoTerminou(response.body())
                    Log.i("TMDB", "Pesquisa Concluida")
                }

                override fun onFailure(call: Call<ListMovieDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                    Log.i("TMDB", "Falha na Pesquisa")
                }

            })
        }
    }

    fun getBuscarPessoas(pesquisa : String, page : Int){
        val call = api.pesquisarPessoa(pesquisa, page)
        runBlocking {
            call.enqueue(object : Callback<ListPeopleDto> {
                override fun onResponse(
                        call: Call<ListPeopleDto>,
                        response: Response<ListPeopleDto>
                ) {
                    peopleListener.requisicaoTerminou(response.body())
                    Log.i("TMDB", "Pesquisa Concluida")
                }

                override fun onFailure(call: Call<ListPeopleDto>, t: Throwable) {
                    peopleListener.falhaRequisicao(t.message)
                    Log.i("TMDB", "Falha na Pesquisa")
                }

            })
        }
    }

    fun getBuscarSeries(pesquisa : String, page : Int){
        val call = api.pesquisarSerie(pesquisa, page)
        runBlocking {
            call.enqueue(object : Callback<ListSeriesDto> {
                override fun onResponse(
                    call: Call<ListSeriesDto>,
                    response: Response<ListSeriesDto>
                ) {
                    seriesListener.requisicaoTerminouSeries(response.body())
                    Log.i("TMDB", "Pesquisa Concluida")
                }

                override fun onFailure(call: Call<ListSeriesDto>, t: Throwable) {
                    seriesListener.falhaRequisicaoSeries(t.message)
                    Log.i("TMDB", "Falha na Pesquisa")
                }

            })
        }
    }

}