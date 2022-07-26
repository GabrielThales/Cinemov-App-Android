package br.thales.cinemov.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.R
import br.thales.cinemov.databinding.MainFragmentBinding
import br.thales.cinemov.databinding.SeasonDetailsFragmentBinding

class SeasonDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SeasonDetailsFragment()
    }

    private lateinit var viewModel: SeasonDetailsViewModel
    private var _binding: SeasonDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = SeasonDetailsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeasonDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}