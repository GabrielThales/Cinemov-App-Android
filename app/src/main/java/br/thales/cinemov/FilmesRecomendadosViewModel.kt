package br.thales.cinemov

import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.Infra.Repository.AvaliacaoRepository
import br.thales.cinemov.presentation.RecyclerViews.MovieAdapter
import com.google.firebase.auth.FirebaseAuth

class FilmesRecomendadosViewModel : ViewModel() {
    val avaliacaoRepository = AvaliacaoRepository()
    val filmeService = FilmeService()
    var movieAdapter = MovieAdapter()
    init {
        avaliacaoRepository.getMinhasAvaliacoes(FirebaseAuth.getInstance().uid.toString())
    }
}