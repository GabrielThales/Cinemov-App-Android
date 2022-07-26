package br.thales.cinemov.Application.Services

import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.GenerosTMDBApi
import br.thales.cinemov.Application.Interfaces.RetrofitServicesListener
import kotlinx.coroutines.runBlocking
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GeneroService {

    private var api : GenerosTMDBApi
    private lateinit var listener : GeneroServiceListener

    companion object{
        val instance = GeneroService()
    }


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(GenerosTMDBApi::class.java)
    }

    public fun setGeneroServiceListener(listener: GeneroServiceListener){
        this.listener = listener
    }

    fun getGenerosFilmes() {
        val call = api.getGenerosFilmes()

        runBlocking {
            call!!.enqueue(object : Callback<GenerosDto?>{
                override fun onResponse(call: Call<GenerosDto?>, response: Response<GenerosDto?>) {
                    if (response.isSuccessful){
                        listener.requisicaoTerminou(response.body())
                    }
                }

                override fun onFailure(call: Call<GenerosDto?>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }

    }

    fun getGenerosSeries() {
        val call = api.getGenerosSerie()

        runBlocking {
            call!!.enqueue(object : Callback<GenerosDto?>{
                override fun onResponse(call: Call<GenerosDto?>, response: Response<GenerosDto?>) {
                    if (response.isSuccessful){
                        listener.requisicaoTerminou(response.body())
                    }
                }

                override fun onFailure(call: Call<GenerosDto?>, t: Throwable) {
                    listener.falhaRequisicao(t.message)
                }

            })
        }
    }
}