package br.thales.cinemov.Infra.Repository

import android.util.Log
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Domain.Interface.IUsuarioRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.security.auth.callback.Callback

class UsuarioRepository : IUsuarioRepository {

    lateinit var usuarioEventListener : UsuarioEventListener

    var db = FirebaseFirestore.getInstance().collection("usuarios")

    companion object{
        val instance = UsuarioRepository()
    }

    override fun Criar(t: Usuario) {
        db.document(t.id .toString()).set(t).addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
        }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

    }

    fun setEventListener(eventListener: UsuarioEventListener){
        usuarioEventListener = eventListener
    }


    override fun Get(uuid: String){
        val document = db.document(uuid)

        document.get().addOnSuccessListener {
            if (it != null){
                 usuarioEventListener.requisicaoFirebaseTerminou( it.toObject<Usuario>())
            }
        }.addOnFailureListener {
        }

    }

    override fun Update(t: Usuario) {
        db.document(t.id .toString()).set(t).addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
        }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }

    }

    override fun Excluir(t: Usuario) {
        db.document(t.id.toString()).delete().addOnSuccessListener {
            usuarioEventListener.requisicaoFirebaseTerminou(t)
        }.addOnFailureListener {
            usuarioEventListener.falhaRequisicaoFirebase(it.message)
        }
    }

}