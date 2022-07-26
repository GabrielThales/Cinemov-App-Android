package br.thales.cinemov

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.databinding.AvaliacaoFragmentBinding
import br.thales.cinemov.databinding.Cadastro1FragmentBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AvaliacaoFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    companion object {
        fun newInstance() = AvaliacaoFragment()
    }

    private lateinit var viewModel: AvaliacaoViewModel
    private var _binding: AvaliacaoFragmentBinding? = null
    private val binding get() = _binding!!
    private var notaFilme = 0.0
    var pesquisaFav = false

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AvaliacaoFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("TAG", adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("TAG", "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AvaliacaoViewModel::class.java)
        viewModel.activity = requireActivity()
        //viewModel.context = this.context

        binding.skbNotaFilme.setOnSeekBarChangeListener(this)

        try {
            if (arguments != null) {

                viewModel.movie = requireArguments().getParcelable<MovieDto>("movie")!!
                pesquisaFav = requireArguments().getBoolean("pesquisaFav")

                binding.txtFilme.setText(viewModel.movie.title)
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + viewModel.movie.poster_path).into(binding.imgFilme)

            }

            binding.ckbRecomenda.isChecked

            binding.btnAvaliar.setOnClickListener {
                var avaliacao : Avaliacao? = null


                val titulo = binding.txtAvaliacaoTitulo.text
                val avaliacaoTexto = binding.txtAvaliacao.text

                if (titulo.isEmpty() || avaliacaoTexto.isEmpty()){
                    Toast.makeText(this.context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                } else {

                    val date = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy 'às' hh:mm a")
                    val formattedDate = date.format(formatter)

                    Toast.makeText(requireContext(), formattedDate.toString(), Toast.LENGTH_LONG).show()

                    avaliacao = Avaliacao(UUID.randomUUID().toString(), MainActivityViewModel.usuario!!.id, MainActivityViewModel.usuario!!.nome.toString() , MainActivityViewModel.usuario!!.fotoUri.toString(), viewModel.movie, titulo.toString(), avaliacaoTexto.toString(),
                            notaFilme, binding.ckbRecomenda.isChecked, formattedDate.toString())

                    viewModel.avaliarFilme(avaliacao)

                    if (pesquisaFav){
                        PesquisaViewModel.listFav.add(avaliacao.movieDto!!)
                    }

                    if (mInterstitialAd != null && pesquisaFav == false) {
                        mInterstitialAd?.show(requireActivity())
                        requireActivity().finish()
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    }
                }


            }
        } catch (e : Exception){

            if (arguments != null) {

                viewModel.serie = requireArguments().getParcelable<SerieDto>("serie")!!

                binding.txtFilme.setText(viewModel.serie.name)
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + viewModel.serie.poster_path).into(binding.imgFilme)

            }

            binding.ckbRecomenda.isChecked

            binding.btnAvaliar.setOnClickListener {
                var avaliacao: Avaliacao?


                val titulo = binding.txtAvaliacaoTitulo.text
                val avaliacaoTexto = binding.txtAvaliacao.text

                if (titulo.isEmpty() || avaliacaoTexto.isEmpty()){
                    Toast.makeText(this.context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                } else {

                    val date = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy 'às' hh:mm a")
                    val formattedDate = date.format(formatter)

                    Toast.makeText(requireContext(), formattedDate.toString(), Toast.LENGTH_LONG).show()

                    avaliacao = Avaliacao(UUID.randomUUID().toString(), MainActivityViewModel.usuario!!.id, MainActivityViewModel.usuario!!.nome.toString() , MainActivityViewModel.usuario!!.fotoUri.toString(), null, titulo.toString(), avaliacaoTexto.toString(),
                            notaFilme, binding.ckbRecomenda.isChecked, formattedDate.toString(), viewModel.serie)

                    viewModel.avaliarFilme(avaliacao!!)
                    

                    if (mInterstitialAd != null && pesquisaFav == false) {
                        mInterstitialAd?.show(requireActivity())
                        requireActivity().finish()
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    }
                }


            }
        }


        // TODO: Use the ViewModel
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        notaFilme = progress/10.0
        binding.txtNotaFilme.setText("Nota: ${notaFilme}")
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

}