package br.thales.cinemov

import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Services.PessoaService
import br.thales.cinemov.presentation.RecyclerViews.KnowForAdapter

class PeopleDetailsViewModel : ViewModel() {
    val peopleService = PessoaService()
    val knowForAdapter = KnowForAdapter()
}