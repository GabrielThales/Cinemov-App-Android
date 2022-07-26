package br.thales.cinemov.Application.Services

import br.thales.cinemov.Application.Dtos.Movie.*
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Dtos.Series.SerieDetailsDto
import br.thales.cinemov.Application.Interfaces.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SerieService {
    private var api : SerieTMDBApi
    private lateinit var listener : SerieDetailsServiceListener
    private lateinit var watchProvidersListener : WatchProvidersListener
    private lateinit var serieRecomendacoesListener : SeriesRecomendacoesListener
    private lateinit var creditsListener: CreditsListener

    companion object{
        val instance = FilmeService()
    }


    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(SerieTMDBApi::class.java)
    }

    fun setEventListener(eventListener : SerieDetailsServiceListener){
        listener = eventListener
    }

    fun setEventListenerWatchProvider(eventListener : WatchProvidersListener){
        watchProvidersListener = eventListener
    }

    fun setEventListenerSerieRecomendacoes(eventListener : SeriesRecomendacoesListener){
        serieRecomendacoesListener = eventListener
    }

    fun setEventListenerCredits(eventListener : CreditsListener){
        creditsListener = eventListener
    }


    fun getSerieDetails(id : Int){
        val call = api.getSerieDetails(id)

        runBlocking {
            call!!.enqueue(object : Callback<SerieDetailsDto> {
                override fun onResponse(
                        call: Call<SerieDetailsDto>,
                        response: Response<SerieDetailsDto>
                ) {
                    listener.requisicaoTerminouSeriDetails(response.body()!!)
                }

                override fun onFailure(call: Call<SerieDetailsDto>, t: Throwable) {
                    listener.falhaRequisicaoSerieDetails(t.message)
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


                    watchProvidersListener.watchProvidersReq(listProviders)
                }

                override fun onFailure(call: Call<WatchProviderListDto>, t: Throwable) {
                    watchProvidersListener.falhaRequisicaoWp(t.message)
                }

            })
        }
    }

    fun getRecomendacoes(id : Int){
        val call = api.getRecomendacoes(id)
        runBlocking {
            call.enqueue(object : Callback<ListSeriesDto>{
                override fun onResponse(
                        call: Call<ListSeriesDto>,
                        response: Response<ListSeriesDto>
                ) {
                    serieRecomendacoesListener.requisicaoSerieRecomendacoesTerminou(response.body())
                }

                override fun onFailure(call: Call<ListSeriesDto>, t: Throwable) {
                    listener.falhaRequisicaoSerieDetails(t.message)
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
                        creditsListener.reqCreditsTerminou(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CreditsDto>, t: Throwable) {
                    creditsListener.falhaRequisicao(t.message)
                }

            })
        }
    }

}