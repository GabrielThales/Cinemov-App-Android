package br.thales.cinemov

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.thales.cinemov.Application.Dtos.Movie.CreditsDto
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDetailsDto
import br.thales.cinemov.Application.Dtos.Movie.Provider
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.Infra.Interfaces.ListAvaliacoesEventListener
import br.thales.cinemov.databinding.HomeFragmentBinding
import br.thales.cinemov.databinding.RecomendacoesFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class RecomendacoesFragment : Fragment() {

    companion object {
        fun newInstance() = RecomendacoesFragment()
    }

    private lateinit var viewModel: RecomendacoesViewModel
    private var _binding: RecomendacoesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecomendacoesFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecomendacoesViewModel::class.java)


        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        val arrayFragments = arrayListOf<Fragment>(HomeFragment.newInstance(), FilmesRecomendadosFragment.newInstance())
        val adapter = TabAdapter(requireActivity())
        adapter.arrayFragments = arrayFragments


        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, possition ->
            viewPager2.setCurrentItem(tab.position, false)
            if (possition == 0) tab.setText("Novidades")
            if (possition == 1) tab.setText("Recomendações")

        }.attach()

    }



}