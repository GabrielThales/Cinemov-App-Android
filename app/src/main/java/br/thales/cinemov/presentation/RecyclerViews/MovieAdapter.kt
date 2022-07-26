package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.Movie.ListMovieDto
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    var listMovieDto: List<MovieDto> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var itemListener : RecyclerViewItemListener


    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_rcv, parent, false)

        return MovieAdapter.MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(listMovieDto[position], itemListener, position)
    }


    override fun getItemCount(): Int {
       return listMovieDto.size
    }

    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: MovieDto, itemListener: RecyclerViewItemListener, position: Int){

            val imgPoster : ImageView = itemView.findViewById(R.id.imgPoster)
            val txtNomeFilme : TextView = itemView.findViewById(R.id.txtNomeFilme)
            val txtDescicao : TextView = itemView.findViewById(R.id.txtDescricaoFilme)

            txtNomeFilme.setText(movie.title)
            if (movie.overview.isNullOrEmpty()){
                txtDescicao.setText("Descrição não disponivel :(")
            } else {
                txtDescicao.setText(movie.overview)
            }

            if (movie.poster_path.isNullOrEmpty()){
                //Picasso.get().load(viewModel.usuario!!.fotoUri).transform(CropCircleTransformation()).into(binding.imgFotoUsuario)
            } else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(imgPoster)
            }

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }





        }
        }
    }

