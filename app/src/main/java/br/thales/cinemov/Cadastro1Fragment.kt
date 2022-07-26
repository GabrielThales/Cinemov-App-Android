package br.thales.cinemov

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Services.StorageService
import br.thales.cinemov.Domain.Entities.Usuario
import br.thales.cinemov.databinding.Cadastro1FragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.w3c.dom.Text
import java.io.IOException


class Cadastro1Fragment : Fragment(), RecyclerViewItemListener, View.OnKeyListener, TextWatcher {

    companion object {
        fun newInstance() = Cadastro1Fragment()
    }

    private lateinit var viewModel: Cadastro1ViewModel
    private var _binding: Cadastro1FragmentBinding? = null
    private val binding get() = _binding!!
    private var filePath: Uri? = null

    private val PICK_IMAGE_REQUEST = 71


    var filmesEscolhidos : MutableList<MovieDto> = mutableListOf<MovieDto>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //val view = inflater.inflate(R.layout.cadastro1_fragment, container, false)
        _binding = Cadastro1FragmentBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(Cadastro1ViewModel::class.java)

        binding.txtNomeUser.setText(viewModel.user.displayName)

//        binding.txtDataNascimento.setOnKeyListener(this)
        binding.txtDataNascimento.addTextChangedListener(this)


        binding.txtDataNascimento.inputType = InputType.TYPE_CLASS_DATETIME


        Log.i("Cadastro1", "Passando pelo Fragment Cadastro 1")

        binding.btnAvancar.setOnClickListener {
            val navController = findNavController()
            val bundle = Bundle()
            bundle.putBoolean("pesquisaFav", true)
            //navController.navigate(R.id.action_cadastro1Fragment_to_pesquisaFragment, bundle)

            val intent = Intent(requireContext(), MainActivity::class.java)


            val usuario = Usuario(viewModel.user.uid, binding.txtDataNascimento.text.toString(), binding.txtNomeUser.text.toString(), viewModel.uriStorage.toString(), null, "Legal", 0 )

            viewModel.criarUsuario(usuario)

            startActivity(intent)
            requireActivity().finish()

        }

//        binding.btnBusca.setOnClickListener {
//            val busca = binding.txtBuscaFilme.text
//
//            if (!busca.isNullOrEmpty()){
//                viewModel.searchService.getBuscaFilme(busca.toString(), 1)
//
//                val gridLayoutManager = GridLayoutManager(context, 3)
//                gridLayoutManager.orientation = GridLayoutManager.VERTICAL
//
//                val linearLayoutManager = LinearLayoutManager(context)
//                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//                binding.rcvFilmesPesquisados.layoutManager =  linearLayoutManager
//
//                binding.rcvFilmesPesquisados.adapter = viewModel.movieSearchAdapter
//                viewModel.movieSearchAdapter.setRecyclerViewItemListener(this)
//            } else {
//                Toast.makeText(requireContext(), "Digite o nome do Filme", Toast.LENGTH_SHORT).show()
//            }
//        }




        binding.imgEscolherFoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Escolha sua foto de Perfil"), PICK_IMAGE_REQUEST)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                binding.imgEscolherFoto.setImageURI(filePath)
                val user = FirebaseAuth.getInstance().currentUser
                viewModel.uploadImage(filePath, user!!.uid)
//                uriStorage =  viewModel.getImage(user.uid)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    override fun recyclerViewItemClicked(view: View, id: Int) {
//        view.setBackgroundColor(Color.LTGRAY)
//        view.isClickable = false
//
//            generosEscolhidos.add(viewModel.generos!!.genres[id])
//
//
//        binding.txtFilmesEscolhidos.text = "${binding.txtFilmesEscolhidos.text}, ${viewModel.generos!!.genres[id].name}"

//        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
//        intent.putExtra("movie", viewModel.movieSearchAdapter.listMovieDto[id])
//
//        if(filmesEscolhidos.size == 3){
//
//            binding.rcvFilmesPesquisados.visibility = View.GONE
//
//
//        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        val texto = binding.txtDataNascimento.text
        Toast.makeText(requireContext(), keyCode.toString(), Toast.LENGTH_SHORT).show()
        val tamanhoTexto = binding.txtDataNascimento.text.length
//        if (texto.isNullOrEmpty() || keyCode == KeyEvent.KEYCODE_CLEAR){
//            return false
//        } else
        if ((tamanhoTexto == 2 || tamanhoTexto == 5) && keyCode != KeyEvent.KEYCODE_DEL){
            binding.txtDataNascimento.text = texto.append("/")
            binding.txtDataNascimento.setSelection(texto.length)
        }
        return false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s?.length == 2 || s?.length == 5){
            binding.txtDataNascimento.text = binding.txtDataNascimento.text.append("/")
            binding.txtDataNascimento.setSelection(s.length)
        }

    }

    override fun afterTextChanged(s: Editable?) {
    }

}