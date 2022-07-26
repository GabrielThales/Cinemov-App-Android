package br.thales.cinemov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.databinding.ActivityMainBinding
import br.thales.cinemov.databinding.ActivitySeriesDetailsBinding
import br.thales.cinemov.ui.main.SeriesDetailsFragment2
import com.google.android.material.tabs.TabLayoutMediator

class SeriesDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeriesDetailsBinding
    private lateinit var viewModel: SeriesDetailsActivityViewModel
    private lateinit var serie : SerieDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_details)
        binding = ActivitySeriesDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(SeriesDetailsActivityViewModel::class.java)

        val tabLayout = binding.tabLayout2
        val viewPager2 = binding.viewPager2
        viewPager2.isUserInputEnabled = false

        if (intent != null){
            serie = intent.getParcelableExtra<SerieDto>("serie")!!
        }


        val arrayFragments = arrayListOf<Fragment>(SeriesDetailsFragment2.newInstance(), CastAndCrewFragment.newInstance(), AvaliacaoFragment.newInstance())
        val bundle = Bundle()
        bundle.putParcelable("serie", serie)
        arrayFragments[0].arguments = bundle
        arrayFragments[1].arguments = bundle
        arrayFragments[2].arguments = bundle
        val adapter = TabAdapter(this)
        adapter.arrayFragments = arrayFragments

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, possition ->
            viewPager2.setCurrentItem(tab.position, true)
            if (possition == 0) tab.setText("Serie")
            if (possition == 1) tab.setText("Equipe")
            if (possition == 2) tab.setText("Avaliar")
        }.attach()

    }
}