package br.thales.cinemov

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.databinding.CastAndCrewFragmentBinding
import br.thales.cinemov.databinding.UsuarioFragmentBinding

class CastAndCrewFragment : Fragment() {

    companion object {
        fun newInstance() = CastAndCrewFragment()
    }

    private lateinit var viewModel: CastAndCrewViewModel
    private var _binding: CastAndCrewFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = CastAndCrewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CastAndCrewViewModel::class.java)

        if (arguments != null){

            try {
                val movie = requireArguments().getParcelable<MovieDto>("movie")
                viewModel.filmeSerivce.getCredits(movie!!.id!!)

                val layoutManager = GridLayoutManager(context, 3)
                layoutManager.orientation = GridLayoutManager.VERTICAL

                val layoutManager2 = GridLayoutManager(context, 3)
                layoutManager2.orientation = GridLayoutManager.VERTICAL

                binding.rcvCast.layoutManager = layoutManager
                binding.rcvCast.adapter = viewModel.castAdapter

                binding.rcvCrew.layoutManager = layoutManager2
                binding.rcvCrew.adapter = viewModel.crewAdapter

            } catch (e : Exception){

                val serie = requireArguments().getParcelable<SerieDto>("serie")
                viewModel.serieService.getCredits(serie!!.id)

                val layoutManager = GridLayoutManager(context, 3)
                layoutManager.orientation = GridLayoutManager.VERTICAL

                val layoutManager2 = GridLayoutManager(context, 3)
                layoutManager2.orientation = GridLayoutManager.VERTICAL

                binding.rcvCast.layoutManager = layoutManager
                binding.rcvCast.adapter = viewModel.castAdapter

                binding.rcvCrew.layoutManager = layoutManager2
                binding.rcvCrew.adapter = viewModel.crewAdapter
            }

        }


    }

}