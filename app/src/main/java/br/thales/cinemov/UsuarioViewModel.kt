package br.thales.cinemov

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Infra.Interfaces.AvaliacaoEventListener
import br.thales.cinemov.Infra.Interfaces.ListAvaliacoesEventListener
import br.thales.cinemov.Infra.Repository.AvaliacaoRepository
import br.thales.cinemov.Infra.Repository.UsuarioRepository
import br.thales.cinemov.presentation.RecyclerViews.AvaliacaoAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class UsuarioViewModel : ViewModel(), RecyclerViewItemListener, ListAvaliacoesEventListener {

    var usuario = MainActivityViewModel.usuario
    var usuarioRepository: UsuarioRepository = UsuarioRepository()
    val avaliacaoAdapter = AvaliacaoAdapter()
    val avaliacaoRepository = AvaliacaoRepository()

    init {
        avaliacaoAdapter.setRecyclerViewItemListener(this)
        avaliacaoRepository.setListEventListener(this)

        if (usuario?.id != null){
            avaliacaoRepository.getMinhasAvaliacoes(usuario!!.id.toString())
        } else {
            val user = FirebaseAuth.getInstance().currentUser
            avaliacaoRepository.getMinhasAvaliacoes(user!!.uid)
        }


        //usuarioRepository.Get(FirebaseAuth.getInstance().currentUser!!.uid)

    }


    override fun requisicaoFirebaseTerminou(t: List<Avaliacao>?) {
        if (t != null) {
            avaliacaoAdapter.listAvaliacao = t.reversed()
        }
    }


    override fun falhaRequisicaoFirebase(t: String?) {
        Log.e("Usuario:", t.toString())
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        Log.i("ItemClicked", "FODASE")
    }


}