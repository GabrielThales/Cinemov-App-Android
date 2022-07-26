package br.thales.cinemov.Application.Services

import android.net.Uri
import android.util.Log
import br.thales.cinemov.Application.Interfaces.StorageEventListener
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class StorageService {

    lateinit var storageListener : StorageEventListener

    val storageRef = Firebase.storage.reference

    companion object {
        val USUARIOS_REF = "usuarios/"

        //val PROFILE_REF = "/profile"
        val instance = StorageService()
    }

    fun setStorageEventListener(listener: StorageEventListener){
        storageListener = listener
    }

    fun uploadImageToFirebaseStorage(uri: Uri?, uid: String) {
        val usuariosRef = storageRef.child("${USUARIOS_REF}${uid}/profile")

        usuariosRef.putFile(uri!!).addOnCompleteListener {
            Log.i("StorageTeste", "UploadCompleto")
            //it.result?.storage?.downloadUrl
        }.addOnFailureListener {
            Log.e("StorageTeste", "Erro: ${it.message}")
        }.addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                Log.i("StorageTeste", "Link Download: ${it.toString()}")
                storageListener.requisicaoFirebaseTerminou(it)
            }


        }
    }

//        suspend fun getImageFromFirebaseStorage(uid: String): Uri? {
//
//
//            return suspendCoroutine { storageRef.child("${USUARIOS_REF}${uid}/profile").downloadUrl }
//        }

    }
