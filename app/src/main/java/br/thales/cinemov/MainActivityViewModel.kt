package br.thales.cinemov

import android.util.Log
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Infra.Interfaces.AvaliacaoEventListener
import br.thales.cinemov.Infra.Repository.UsuarioRepository
import com.google.firebase.auth.FirebaseAuth

class MainActivityViewModel : ViewModel(), UsuarioEventListener {
    var usuarioRepository: UsuarioRepository = UsuarioRepository()
    var listFav = false

    companion object{
        var usuario : Usuario? = null
    }

    init {
        usuarioRepository.setEventListener(this)
        usuarioRepository.Get(FirebaseAuth.getInstance().currentUser!!.uid)
    }
    override fun requisicaoFirebaseTerminou(t: Usuario?) {
        usuario = t

        t?.listFav = PesquisaViewModel.listFav
        if (listFav){
            usuarioRepository.Update(t!!)
        }
        //Log.i("UsuarioMainActivity:", "${usuario!!.nome}, ${usuario!!.fotoUri}")
    }


    override fun falhaRequisicaoFirebase(t: String?) {
        TODO("Not yet implemented")
    }
}