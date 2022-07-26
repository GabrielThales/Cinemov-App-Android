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
import br.thales.cinemov.Application.Dtos.Series.Season
import br.thales.cinemov.Application.Interfaces.FilmeServiceListener
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>(){

    var listSeasonDto: List<Season> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var itemListener : RecyclerViewItemListener


    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.temporada_rcv, parent, false)

        return SeasonAdapter.SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bindItem(listSeasonDto[position], itemListener, position)
    }


    override fun getItemCount(): Int {
        return listSeasonDto.size
    }

    class SeasonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(season : Season, itemListener: RecyclerViewItemListener, position: Int){

            val imgPoster : ImageView = itemView.findViewById(R.id.imgSeason)
            val txtNomeFilme : TextView = itemView.findViewById(R.id.txtSeasonName)
            val txtDescicao : TextView = itemView.findViewById(R.id.txtSeasonOverview)
            val txtAirDate : TextView = itemView.findViewById(R.id.txtSeasonAirDate)
            val txtEpNumber : TextView = itemView.findViewById(R.id.txtSeasonEpNumber)

            txtNomeFilme.setText(season.name)
            if (season.overview.isNullOrEmpty()){
                txtDescicao.setText("Descrição não disponivel :(")
            } else {
                txtDescicao.setText(season.overview)
            }

            if (season.poster_path.isNullOrEmpty()){
                //Picasso.get().load(viewModel.usuario!!.fotoUri).transform(CropCircleTransformation()).into(binding.imgFotoUsuario)
            } else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + season.poster_path).into(imgPoster)
            }

            txtAirDate.text = season.air_date
            txtEpNumber.text = "Episódios: ${season.episode_count}"

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }


        }
    }
}

