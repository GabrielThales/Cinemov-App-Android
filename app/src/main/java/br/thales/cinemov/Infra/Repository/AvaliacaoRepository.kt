package br.thales.cinemov.Infra.Repository

import android.util.Log
import br.thales.cinemov.Application.Interfaces.RetrofitServicesListener
import br.thales.cinemov.Application.Interfaces.UsuarioEventListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Domain.Interface.IAvaliacaoRepository
import br.thales.cinemov.Infra.Interfaces.AvaliacaoEventListener
import br.thales.cinemov.Infra.Interfaces.ListAvaliacoesEventListener
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AvaliacaoRepository : IAvaliacaoRepository {

    lateinit var avaliacaoEventListener : AvaliacaoEventListener
    lateinit var listAvaliacaoEventListener: ListAvaliacoesEventListener

    var dbFilmes = FirebaseFirestore.getInstance().collection("avaliacoesFilmes")

    companion object{
        val instance = UsuarioRepository()
    }

    override fun getMinhasAvaliacoes(uuid: String) {
        dbFilmes.whereEqualTo("autorId", uuid).addSnapshotListener { documentSnapshots, e ->
            if (e != null) {
                Log.e("TAG", "Listen failed!", e)
                listAvaliacaoEventListener.falhaRequisicaoFirebase(e.message)
            }


            val list = documentSnapshots!!.toObjects(Avaliacao::class.java)

            //val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy 'às' hh:mm a")
            //val formattedDate = date.format(formatter)

            listAvaliacaoEventListener.requisicaoFirebaseTerminou(list.sortedBy { t -> LocalDateTime.parse(t.data, formatter) })

//                for (doc in documentSnapshots!!) {
//                    val loja = doc.toObject(Loja::class.java)
//                    Log.i("Loja", loja.Nome.toString())
//                    lojas?.add(loja)
//                }


        }
    }

    fun setEventListener(eventListener: AvaliacaoEventListener){
        avaliacaoEventListener = eventListener
    }

    fun setListEventListener(eventListener: ListAvaliacoesEventListener){
        listAvaliacaoEventListener = eventListener
    }

    override fun Criar(t: Avaliacao) {
            dbFilmes.document(t.id.toString()).set(t).addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference}")
            }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }

    }

    override fun Get(uuid: String) {
        val document = dbFilmes.document(uuid)
        document.get().addOnSuccessListener {
            if (it != null){
                avaliacaoEventListener.requisicaoFirebaseTerminou( it.toObject<Avaliacao>())
            }
        }.addOnFailureListener {
            avaliacaoEventListener.falhaRequisicaoFirebase(it.message)
        }
    }

    override fun Update(t: Avaliacao) {
        TODO("Not yet implemented")
    }

    override fun Excluir(t: Avaliacao) {
        TODO("Not yet implemented")
    }
}