package br.thales.cinemov.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.Application.Dtos.Movie.Provider
import br.thales.cinemov.Application.Dtos.Series.ListSeriesDto
import br.thales.cinemov.Application.Dtos.Series.SerieDetailsDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.Application.Interfaces.SerieDetailsServiceListener
import br.thales.cinemov.Application.Interfaces.SeriesRecomendacoesListener
import br.thales.cinemov.Application.Interfaces.WatchProvidersListener
import br.thales.cinemov.Application.Services.GeneroService
import br.thales.cinemov.PesquisaViewModel
import br.thales.cinemov.R
import br.thales.cinemov.SeriesDetailsActivity
import br.thales.cinemov.SeriesDetailsActivityViewModel
import br.thales.cinemov.databinding.ActivitySeriesDetailsBinding
import br.thales.cinemov.databinding.MainFragmentBinding
import br.thales.cinemov.databinding.PesquisaFragmentBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.google.api.Distribution
import com.squareup.picasso.Picasso

class SeriesDetailsFragment2 : Fragment(), RecyclerViewItemListener, SerieDetailsServiceListener, WatchProvidersListener, SeriesRecomendacoesListener {

    companion object {
        fun newInstance() = SeriesDetailsFragment2()
    }

    private lateinit var viewModel: SerieDetailsFragmentViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var serie : SerieDto
    lateinit var navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SerieDetailsFragmentViewModel::class.java)
        viewModel.generoAdapterSeries.setRecyclerViewItemListener(this)
        viewModel.createdByAdapter.setRecyclerViewItemListener(this)
        viewModel.watchProvidersAdapter.setRecyclerViewItemListener(this)
        viewModel.serieAdapter.setRecyclerViewItemListener(this)
        viewModel.serieService.setEventListenerWatchProvider(this)
        viewModel.serieService.setEventListener(this)
        viewModel.serieService.setEventListenerSerieRecomendacoes(this)
        viewModel.seasonAdapter.setRecyclerViewItemListener(this)
        navController = NavController(requireContext())


        if(arguments != null){
             val serie = requireArguments().getParcelable<SerieDto>("serie")

            if (serie != null){
                this.serie = serie
                viewModel.serieService.getSerieDetails(serie.id)
                viewModel.serieService.getWatchProviders(serie.id)
                viewModel.serieService.getRecomendacoes(serie.id)

                binding.txtSerieTitle.text = serie.name
                binding.txtFirstAirDate.text = serie.first_air_date
                binding.txtOverviewSerie.text = serie.overview
                binding.txtSerieVoteAverage.text = serie.vote_average.toString()

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + serie.poster_path).into(binding.imgSeriesPoster)

                val linearLayoutVertical = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                val linearLayoutHorizontal = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rcvSeriesGenre.layoutManager = linearLayoutVertical
                binding.rcvSeriesGenre.adapter = viewModel.generoAdapterSeries

                binding.rcvWatchProvidersSeries.layoutManager = linearLayoutHorizontal
                binding.rcvWatchProvidersSeries.adapter = viewModel.watchProvidersAdapter

                binding.rcvCreatedBy.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rcvCreatedBy.adapter = viewModel.createdByAdapter

                binding.rcvSeriesRecomendadas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rcvSeriesRecomendadas.adapter = viewModel.serieAdapter

                binding.rcvSeason.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rcvSeason.adapter = viewModel.seasonAdapter


            }
        }
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        //val navController = this.navController
        when(view.id){
            R.id.serieRcv -> startActivity(Intent(requireContext(), SeriesDetailsActivity::class.java).putExtra("serie", viewModel.serieAdapter.listSeries[id] ))
            R.id.temporada_rcv -> navController.navigate(R.id.action_seriesDetailsFragment2_to_seasonDetailsFragment)
        }
    }

    override fun requisicaoTerminouSeriDetails(t: SerieDetailsDto?) {
        if(t != null){
            //viewModel.serieDetails = t
            viewModel.generoAdapterSeries.listGenre = t.genres
            binding.txtTagLine.text = t.tagline
            binding.txtEpCount.text = "Numero de Episódios: " + t.number_of_episodes.toString()
            viewModel.createdByAdapter.listCast = t.created_by
            viewModel.seasonAdapter.listSeasonDto = t.seasons
        }
    }

    override fun falhaRequisicaoSerieDetails(t: String?) {
        Log.e("SerieDetail", t.toString())
    }

    override fun watchProvidersReq(listProviders: List<Provider>) {
        viewModel.watchProvidersAdapter.listProviders = listProviders
    }

    override fun falhaRequisicaoWp(t: String?) {
        TODO("Not yet implemented")
    }

    override fun requisicaoSerieRecomendacoesTerminou(t: ListSeriesDto?) {
        if (t != null){
            viewModel.serieAdapter.listSeries = t.results
        }
    }

    override fun falhaSerieRecomendacoes(t: String) {
        TODO("Not yet implemented")
    }

    private fun setIntent(intent : String){
        val intent = Intent()
        intent
    }

}