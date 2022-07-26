package br.thales.cinemov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.databinding.ActivityAvaliacaoDetailsBinding
import br.thales.cinemov.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class AvaliacaoDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: AvaliacaoDetailsViewModel
    private lateinit var binding: ActivityAvaliacaoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvaliacaoDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(AvaliacaoDetailsViewModel::class.java)

        if(intent == null){
            Toast.makeText(this, "Avaliação não encontrada", Toast.LENGTH_LONG).show()
        } else {

            val avaliacao = intent.getBundleExtra("bundleAvaliacao")?.getParcelable<Avaliacao>("avaliacao")!!

            binding.txtAutorNome.text = avaliacao.nomeAutor
            binding.txtAvaliacaoTexto.text = avaliacao.avaliacaoTexto
            binding.txtNotaAvaliada.text = avaliacao.notaFilme.toString()
            binding.txtNomeFilmeAvaliado.text = avaliacao.movieDto?.title
            binding.txtTituloAvaliacao.text = avaliacao.titulo

            Picasso.get().load(avaliacao.authorPhoto).transform(CropCircleTransformation()).into(binding.imgAutorFoto)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + avaliacao.movieDto?.poster_path).into(binding.imgPosterFilmeAvaliado)

        }

    }


}