package br.thales.cinemov

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.thales.cinemov.databinding.ConfigFragmentBinding
import br.thales.cinemov.databinding.FeedFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener

class FeedFragment : Fragment(), RecyclerViewItemListener {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel
    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        TODO("Not yet implemented")
    }

}