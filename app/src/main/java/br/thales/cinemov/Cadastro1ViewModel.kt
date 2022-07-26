package br.thales.cinemov

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.GenerosDto
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Application.Interfaces.GeneroServiceListener
import br.thales.cinemov.Application.Interfaces.SearchServiceEventListener
import br.thales.cinemov.Application.Interfaces.StorageEventListener
import br.thales.cinemov.Application.Services.FilmeService
import br.thales.cinemov.Application.Services.GeneroService
import br.thales.cinemov.Application.Services.PesquisaService
import br.thales.cinemov.Application.Services.StorageService
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.Infra.Repository.UsuarioRepository
import br.thales.cinemov.presentation.RecyclerViews.GeneroAdapter
import br.thales.cinemov.presentation.RecyclerViews.ListMovieAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import br.thales.cinemov.presentation.RecyclerViews.MovieAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.runBlocking

class Cadastro1ViewModel : ViewModel(),SearchServiceEventListener, StorageEventListener {
    var movies : List<MovieDto>? = null
    var generoAdapter: GeneroAdapter = GeneroAdapter()
    var movieSearchAdapter = ListMovieAdapter()
    val searchService = PesquisaService()
    var user = FirebaseAuth.getInstance().currentUser
    var uriStorage : Uri? = null
    lateinit var storageService : StorageService

    init {
//        GeneroService.instance.getGenerosFilmes()
//        GeneroService.instance.setGeneroServiceListener(this)
        searchService.setEventListener(this)
        storageService = StorageService()
        storageService.setStorageEventListener(this)
    }

    override fun requisicaoFirebaseTerminou(t: Uri?) {
        uriStorage = t
        Log.i("FirebaseStorage", t.toString())
    }

    override fun falhaRequisicaoFirebase(t: String?) {
        Log.e("FirebaseStorageErro", t.toString())
    }

    override fun requisicaoTerminou(t: ListMovieDto?) {
        movieSearchAdapter.listMovieDto = t!!.results
        movies = t.results
        movieSearchAdapter.notifyDataSetChanged()
        if(t == null || t.total_results == 0){
            movieSearchAdapter.listMovieDto = t!!.results
            movies = t.results
            movieSearchAdapter.notifyDataSetChanged()
            Log.i("TMDB", "Ok")
        } else {
            Log.i("TMDB", "Vazio")
        }

    }

    override fun falhaRequisicao(t: String?) {
        TODO("Not yet implemented")
    }


    fun uploadImage(filePath : Uri?, uid : String){
        storageService.uploadImageToFirebaseStorage(filePath, uid)
    }

    fun criarUsuario(usuario : Usuario){
        runBlocking {
            UsuarioRepository.instance.Criar(usuario)
        }
    }

//    fun getImage(uid: String) : Uri?{
//       return runBlocking { StorageService.instance.getImageFromFirebaseStorage(uid) }
//    }


}