package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.Cast
import br.thales.cinemov.Application.Dtos.Series.CreatedBy
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class CreatedByAdapter : RecyclerView.Adapter<CreatedByAdapter.CreatedByViewHolder>() {

    var listCast = listOf<CreatedBy>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedByViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.created_by_rcv, parent, false)

        return CreatedByAdapter.CreatedByViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreatedByViewHolder, position: Int) {
        holder.bindItem(listCast[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listCast.size
    }



    class CreatedByViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(createdBy : CreatedBy, itemListener: RecyclerViewItemListener, position: Int) {

            val name = itemView.findViewById<TextView>(R.id.txtNameCreatedBy)
            val profilePhoto = itemView.findViewById<ImageView>(R.id.imgCreatedBy)

            name.text = createdBy.name

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + createdBy.profile_path).into(profilePhoto)


            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(itemView, position)
            }


        }

    }

}