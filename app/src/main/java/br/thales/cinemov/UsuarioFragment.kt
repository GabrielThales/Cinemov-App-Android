package br.thales.cinemov

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.databinding.Cadastro1FragmentBinding
import br.thales.cinemov.databinding.UsuarioFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.GeneroAdapter
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class UsuarioFragment : Fragment(), RecyclerViewItemListener {

    companion object {
        fun newInstance() = UsuarioFragment()
    }

    private lateinit var viewModel: UsuarioViewModel
    private var _binding: UsuarioFragmentBinding? = null
    private val binding get() = _binding!!
    var generoAdapter = GeneroAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UsuarioFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        viewModel.avaliacaoAdapter.setRecyclerViewItemListener(this)

        binding.txtNomeUsuario.setText(viewModel.usuario!!.nome)


        Picasso.get().load(viewModel.usuario!!.fotoUri).transform(CropCircleTransformation()).into(binding.imgFotoUsuario)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(context)
        layoutManager2.orientation = LinearLayoutManager.VERTICAL


//        generoAdapter.listGenre = viewModel!!.usuario!!.generosFavoritos!!

        generoAdapter.setRecyclerViewItemListener(viewModel)

        binding.rcvGenerosFavoritos.adapter = generoAdapter
        binding.rcvGenerosFavoritos.layoutManager = layoutManager

        binding.rcvMinhasAvaliacoes.adapter = viewModel.avaliacaoAdapter
        binding.rcvMinhasAvaliacoes.layoutManager = layoutManager2

        binding.txtDataNascimentoUser.text = viewModel.usuario!!.dataNascimento



    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        //Toast.makeText(requireActivity(),"piru", Toast.LENGTH_LONG).show()
        val intent = Intent(requireActivity(), AvaliacaoDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("avaliacao", viewModel.avaliacaoAdapter.listAvaliacao[id])
        intent.putExtra("bundleAvaliacao", bundle)
        startActivity(intent)
    }

}