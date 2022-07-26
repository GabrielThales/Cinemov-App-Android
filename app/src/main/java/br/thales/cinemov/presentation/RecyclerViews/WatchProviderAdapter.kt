package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.Provider
import br.thales.cinemov.Application.Dtos.Movie.WatchProviderDto
import br.thales.cinemov.Application.Dtos.Movie.WatchProviderListDto
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class WatchProviderAdapter : RecyclerView.Adapter<WatchProviderAdapter.WatchProviderViewHolder>() {

    var listProviders = listOf<Provider>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchProviderViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.watch_providers_rcv, parent, false)

        return WatchProviderAdapter.WatchProviderViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchProviderViewHolder, position: Int) {
        holder.bindItem(listProviders[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listProviders.size
    }


    class WatchProviderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(provider: Provider, itemListener: RecyclerViewItemListener, position: Int) {

            val imgProvider : ImageView = itemView.findViewById(R.id.imgProvider)
            val txtProviderName : TextView = itemView.findViewById(R.id.txtProviderName)

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + provider.logo_path).into(imgProvider)
            txtProviderName.text = provider.provider_name

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }

        }

    }

}