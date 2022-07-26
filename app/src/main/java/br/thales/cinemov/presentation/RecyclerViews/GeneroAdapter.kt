package br.thales.cinemov.presentation.RecyclerViews

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Genre
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class GeneroAdapter : RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    var listGenre = listOf<Genre>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var listGenreIds = listOf<Int>()

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.genero_rcv, parent, false)

        return GeneroAdapter.GeneroViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        holder.bindItem(listGenre[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }



    class GeneroViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(genre: Genre, itemListener: RecyclerViewItemListener, position: Int) {
            val imgGeneroIco : ImageView = itemView.findViewById(R.id.imgGeneroIco)

            val txtGeneroNome : TextView = itemView.findViewById(R.id.txtGeneroNome)
            txtGeneroNome.text = genre.name

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }

            when(genre.name){
                "Ação" -> Picasso.get().load(R.drawable.actionico).into(imgGeneroIco)
                "Aventura" -> Picasso.get().load(R.drawable.adventureico).into(imgGeneroIco)
                "Animação" -> Picasso.get().load(R.drawable.animationico).into(imgGeneroIco)
                "Comédia" -> Picasso.get().load(R.drawable.comedyico).into(imgGeneroIco)
                "Crime" -> Picasso.get().load(R.drawable.crimeico).into(imgGeneroIco)
                "Documentário" -> Picasso.get().load(R.drawable.documentaryico).into(imgGeneroIco)
                "Drama" -> Picasso.get().load(R.drawable.dramaico).into(imgGeneroIco)
                "Família" -> Picasso.get().load(R.drawable.familyico).into(imgGeneroIco)
                "Fantasia" -> Picasso.get().load(R.drawable.fantasyico).into(imgGeneroIco)
                "História" -> Picasso.get().load(R.drawable.historicalico).into(imgGeneroIco)
                "Terror" -> Picasso.get().load(R.drawable.horrorico).into(imgGeneroIco)
                "Música" -> Picasso.get().load(R.drawable.musicalico).into(imgGeneroIco)
                "Mistério" -> Picasso.get().load(R.drawable.spyico).into(imgGeneroIco)
                "Romance" -> Picasso.get().load(R.drawable.romanceico).into(imgGeneroIco)
                "Ficção científica" -> Picasso.get().load(R.drawable.scifiico).into(imgGeneroIco)
                "Cinema TV" -> Picasso.get().load(R.drawable.cinematv).into(imgGeneroIco)
                "Thriller" -> Picasso.get().load(R.drawable.thrillerico).into(imgGeneroIco)
                "Guerra" -> Picasso.get().load(R.drawable.crimeico).into(imgGeneroIco)
                "Faroeste" -> Picasso.get().load(R.drawable.western).into(imgGeneroIco)
                "Action & Adventure" -> Picasso.get().load(R.drawable.actionico).into(imgGeneroIco)
                "Kids" -> Picasso.get().load(R.drawable.kidsico).into(imgGeneroIco)
                "News" -> Picasso.get().load(R.drawable.newsico).into(imgGeneroIco)
                "Reality" -> Picasso.get().load(R.drawable.realityico).into(imgGeneroIco)
                "Sci-Fi & Fantasy" -> Picasso.get().load(R.drawable.scifiico).into(imgGeneroIco)
                "Soap" -> Picasso.get().load(R.drawable.soapico).into(imgGeneroIco)
                "Talk" -> Picasso.get().load(R.drawable.talkico).into(imgGeneroIco)
                "War & Politics" -> Picasso.get().load(R.drawable.politicsico).into(imgGeneroIco)
            }

        }

    }

}