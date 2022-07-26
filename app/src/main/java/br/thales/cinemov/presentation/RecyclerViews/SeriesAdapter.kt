package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Series.SerieDto
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    var listSeries = listOf<SerieDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.series_rcv, parent, false)

        return SeriesAdapter.SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bindItem(listSeries[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listSeries.size
    }

    class SeriesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItem(serie : SerieDto, listener: RecyclerViewItemListener, position: Int){

            val imgSeriePoster : ImageView = itemView.findViewById(R.id.imgSeriePoster)
            val txtSerieName : TextView = itemView.findViewById(R.id.txtSerieName)

            if (serie.name.isNotEmpty()){
                txtSerieName.text = serie.name
            }

            if (!serie.poster_path.isNullOrEmpty()){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + serie.poster_path).into(imgSeriePoster)
            }

            itemView.setOnClickListener {
                listener.recyclerViewItemClicked(it, position)
            }


        }

    }
}