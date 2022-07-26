package br.thales.cinemov

import androidx.lifecycle.ViewModel
import br.thales.cinemov.presentation.RecyclerViews.CardAdapter

class ConfigViewModel : ViewModel() {
    val listTexto = listOf<String>("Tema","Sair do App", "Seguir Usuário", "Sobre")
    val cardAdapter = CardAdapter()

    init {
        cardAdapter.listTexto = listTexto
    }
}