package br.thales.cinemov

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.databinding.ConfigFragmentBinding
import br.thales.cinemov.databinding.UsuarioFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener

class ConfigFragment : Fragment(), RecyclerViewItemListener {

    companion object {
        fun newInstance() = ConfigFragment()
    }

    private lateinit var viewModel: ConfigViewModel
    private var _binding: ConfigFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConfigFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigViewModel::class.java)
        viewModel.cardAdapter.setRecyclerViewItemListener(this)

        binding.rcvCards.adapter = viewModel.cardAdapter

        val gridLayout = GridLayoutManager(requireContext(), 2)
        val linearLayout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rcvCards.layoutManager = linearLayout



    }

    override fun recyclerViewItemClicked(view: View, id: Int) {

        Toast.makeText(requireContext(), "Click : ${viewModel.listTexto[id]}", Toast.LENGTH_SHORT).show()

    }

}