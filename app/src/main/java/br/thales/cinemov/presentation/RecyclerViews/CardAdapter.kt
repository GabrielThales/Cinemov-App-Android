package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var listTexto = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_rcv, parent, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardAdapter.CardViewHolder, position: Int) {
        holder.bindItem(listTexto[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listTexto.size
    }


    class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        fun bindItem(texto : String, itemListener : RecyclerViewItemListener, position: Int){

            val txtTexto : TextView = itemView.findViewById(R.id.txtTexto)
            val imgConfigIco : ImageView = itemView.findViewById(R.id.imgConfigIco)

            txtTexto.text = texto

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }

            when(texto){
                "Sobre" -> Picasso.get().load(R.drawable.aboutico).into(imgConfigIco)
                "Tema" -> Picasso.get().load(R.drawable.cinematv).into(imgConfigIco)
                "Seguir Usuário" -> Picasso.get().load(R.drawable.noirico).into(imgConfigIco)
                "Sair do App" -> Picasso.get().load(R.drawable.crimeico).into(imgConfigIco)
            }

        }

    }

}