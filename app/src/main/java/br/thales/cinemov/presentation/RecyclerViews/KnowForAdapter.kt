package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.Application.Dtos.People.KnownFor
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class KnowForAdapter : RecyclerView.Adapter<KnowForAdapter.KnowForViewHolder>() {

    var listKnowFor = listOf<KnownFor>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowForViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.know_for_rcv, parent, false)

        return KnowForAdapter.KnowForViewHolder(view)
    }

    override fun onBindViewHolder(holder: KnowForViewHolder, position: Int) {
        holder.bindItem(listKnowFor[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listKnowFor.size
    }

    class KnowForViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(knowFor : KnownFor, itemListener : RecyclerViewItemListener, position: Int){
            val txtKnowForName : TextView = itemView.findViewById(R.id.txtKnowForName)
            val txtKnowForMediaType : TextView = itemView.findViewById(R.id.txtKnowForMediaType)
            val imgPosterKnowFor : ImageView = itemView.findViewById(R.id.imgKnowForPoster)

            if (knowFor.media_type == "tv"){
                txtKnowForName.text = knowFor.name
            } else {
                txtKnowForName.text = knowFor.title
            }

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(itemView, position)
            }

            txtKnowForMediaType.text = knowFor.media_type

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + knowFor.poster_path).into(imgPosterKnowFor)

        }
    }

}