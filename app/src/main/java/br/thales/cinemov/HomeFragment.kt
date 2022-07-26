package br.thales.cinemov

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.databinding.HomeFragmentBinding
import br.thales.cinemov.databinding.UsuarioFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener

class HomeFragment : Fragment(), RecyclerViewItemListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL


        viewModel.movieAdapter.setRecyclerViewItemListener(this)

        binding.rcvNovidadesFilmes.adapter = viewModel.movieAdapter
        binding.rcvNovidadesFilmes.layoutManager = layoutManager
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        Log.i("ItemListener", "funcionando: ${id} / ${viewModel.movieAdapter.listMovieDto.size}")

        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movie", viewModel.movieAdapter.listMovieDto[id])
        startActivity(intent)
    }

}