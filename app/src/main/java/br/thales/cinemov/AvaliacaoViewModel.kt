package br.thales.cinemov

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Infra.Interfaces.AvaliacaoEventListener
import br.thales.cinemov.Infra.Repository.AvaliacaoRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AvaliacaoViewModel : ViewModel(), AvaliacaoEventListener {
    var avaliacaoRepository = AvaliacaoRepository()
    lateinit var movie : MovieDto
    lateinit var serie : SerieDto
    lateinit var avaliacao: Avaliacao
    lateinit var activity : FragmentActivity


    override fun requisicaoFirebaseTerminou(t: Avaliacao?) {
        if (t != null) {
            avaliacao = t
            Toast.makeText(activity, "Avaliacão concluida !!", Toast.LENGTH_SHORT).show()
            Log.i("Avaliacao", "pronto")
        }

    }

    override fun falhaRequisicaoFirebase(t: String?) {
        Toast.makeText(activity, "Ocorreu algum problema com a conexão", Toast.LENGTH_SHORT).show()
    }


    fun avaliarFilme(avaliacao : Avaliacao) {
            avaliacaoRepository.Criar(avaliacao)
    }

}