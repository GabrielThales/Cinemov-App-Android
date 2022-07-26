package br.thales.cinemov.Infra.Repository

import android.util.Log
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Domain.Entities.Seguir
import br.thales.cinemov.Domain.Interface.ISeguirRepository
import br.thales.cinemov.Infra.Interfaces.SeguirEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class SeguirRepository : ISeguirRepository {

    var db = FirebaseFirestore.getInstance().collection("seguidores")
    lateinit var seguirEventListener : SeguirEventListener

    companion object{
        val instance = UsuarioRepository()
    }

    fun setEventListener(eventListener: SeguirEventListener){
        seguirEventListener = eventListener
    }


    override fun Criar(t: Seguir) {
        db.document(t.userId.toString()).set(t).addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
            seguirEventListener.requisicaoFirebaseTerminou(t)
        }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                    seguirEventListener.falhaRequisicaoFirebase(e.message)
                }
    }

    override fun Get(uuid: String) {
        val document = db.document(uuid)
        document.get().addOnSuccessListener {
            if (it != null){
                seguirEventListener.requisicaoFirebaseTerminou( it.toObject<Seguir>())
            }
        }.addOnFailureListener {
            seguirEventListener.falhaRequisicaoFirebase(it.message)
        }
    }

    override fun Update(t: Seguir) {
        db.document(t.userId.toString()).set(t).addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
            seguirEventListener.requisicaoFirebaseTerminou(t)
        }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                    seguirEventListener.falhaRequisicaoFirebase(e.message)
                }
    }

    override fun Excluir(t: Seguir) {
        db.document(t.userId.toString()).delete().addOnFailureListener {
            seguirEventListener.requisicaoFirebaseTerminou(t)
        }.addOnFailureListener {
            seguirEventListener.falhaRequisicaoFirebase(it.message)
        }
    }
}