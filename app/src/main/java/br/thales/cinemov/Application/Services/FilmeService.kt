package br.thales.cinemov.Application.Services

import br.thales.cinemov.Application.Dtos.Movie.*
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.GenerosTMDBApi
import br.thales.cinemov.Application.Interfaces.MovieTMDBApi
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmeService {
    private var api : MovieTMDBApi
    private lateinit var listener : FilmeServiceListener

    companion object{
        val instance = FilmeService()
    }


    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(MovieTMDBApi::class.java)
    }

    fun setEventListener(eventListener : FilmeServiceListener){
        listener = eventListener
    }

    fun getNovidades(){

        val call = api.getNovidades()

        runBlocking {
            call!!.enqueue(object : Callback<ListMovieDto> {
                override fun onResponse(call: Call<ListMovieDto>, response: Response<ListMovieDto>) {
                    listener.requisicaoTerminou(response.body())
                }

                override fun onFailure(call: Call<ListMovieDto>, t: Throwable) {
                   listener.falhaRequisicao(t.message)
                }

            })
        }

    }

    fun getMovieDetails(id : Int){
        val call = api.getMovieDetails(id)

        runBlocking {
            call!!.enqueue(object : Callback<MovieDetailsDto>{
                override fun onResponse(
                    call: Call<MovieDetailsDto>,
                    response: Response<MovieDetailsDto>
                ) {
                    listener.movieDetailsReq(response.body()!!)
                }

                override fun onFailure(call: Call<MovieDetailsDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }

    }

    fun getWatchProviders(id : Int){
        val call = api.getWatchProviders(id)

        runBlocking {
            call!!.enqueue(object : Callback<WatchProviderListDto>{
                override fun onResponse(
                    call: Call<WatchProviderListDto>,
                    response: Response<WatchProviderListDto>
                ) {

                    val watchProviders = response.body()
                    var listProviders = arrayListOf<Provider>()
                    val buy = watchProviders!!.results?.BR?.buy
                    val rent = watchProviders.results?.BR?.rent
                    val flat = watchProviders?.results?.BR?.flatrate


                    if (flat != null){
                        listProviders.addAll(flat)
                    }

                    if (buy != null){
                        listProviders.addAll(buy)
                    }

                    if (rent != null){
                        listProviders.addAll(rent)
                    }


                    listener.watchProvidersReq(listProviders)
                }

                override fun onFailure(call: Call<WatchProviderListDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }
    }

    fun getRecomendacoes(id : Int){
        val call = api.getRecomendacoes(id)
        runBlocking {
            call.enqueue(object : Callback<ListMovieDto>{
                override fun onResponse(
                    call: Call<ListMovieDto>,
                    response: Response<ListMovieDto>
                ) {
                    listener.requisicaoTerminou(response.body())
                }

                override fun onFailure(call: Call<ListMovieDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }
    }

    fun getCredits(id : Int){
        val call = api.getCredits(id)
        runBlocking {
            call.enqueue(object : Callback<CreditsDto>{
                override fun onResponse(call: Call<CreditsDto>, response: Response<CreditsDto>) {
                    if (response.body() != null){
                        listener.creditsReq(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CreditsDto>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }
    }

}