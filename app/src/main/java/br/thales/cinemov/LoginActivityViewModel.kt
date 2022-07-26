package br.thales.cinemov

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Interfaces.FirebaseEventListener
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Infra.Repository.UsuarioRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.runBlocking

class LoginActivityViewModel : ViewModel(), GeneroServiceListener, UsuarioEventListener{

    val usuarioService = UsuarioRepository()
    var usuario : Usuario? = null


    override fun requisicaoTerminou(t : GenerosDto?) {
        Log.i("RETROFIT", "Terminou")
    }

    override fun falhaRequisicao(t: String?) {
        Log.e("RETROFIT", t.toString())
    }

    override fun requisicaoFirebaseTerminou(t: Usuario?) {
        if (t != null){
            usuario = t
            Log.i("FirebaseListener", t.nome.toString())
        }
    }

    override fun falhaRequisicaoFirebase(t: String?) {
        TODO("Not yet implemented")
    }


}