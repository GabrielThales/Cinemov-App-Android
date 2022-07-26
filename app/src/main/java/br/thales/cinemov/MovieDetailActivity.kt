package br.thales.cinemov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.databinding.ActivityLoginBinding
import br.thales.cinemov.databinding.ActivityMovieDetailBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.tabs.TabLayoutMediator

class MovieDetailActivity : AppCompatActivity() {

    lateinit var viewModel : MovieDetailViewModel
    lateinit var binding : ActivityMovieDetailBinding
    lateinit var movie : MovieDto
    var pesquisaFav : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView2.loadAd(adRequest)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        if (intent != null){
            movie = intent.getParcelableExtra<MovieDto>("movie")!!
            pesquisaFav = intent.getBooleanExtra("pesquisaFav", false)
        }

        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2
        viewPager2.isUserInputEnabled = false

        val arrayFragments = arrayListOf<Fragment>(MovieDetailsFragment.newInstance(), CastAndCrewFragment.newInstance(), AvaliacaoFragment.newInstance())
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        bundle.putBoolean("pesquisaFav", pesquisaFav)
        arrayFragments[0].arguments = bundle
        arrayFragments[1].arguments = bundle
        arrayFragments[2].arguments = bundle
        val adapter = TabAdapter(this)
        adapter.arrayFragments = arrayFragments

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, possition ->
            viewPager2.setCurrentItem(tab.position, true)
            if (possition == 0) tab.setText("Detalhes")
            if (possition == 1) tab.setText("Equipe")
            if (possition == 2) tab.setText("Avaliar")
        }.attach()


    }

    
}