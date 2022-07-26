package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.Crew
import br.thales.cinemov.Application.Dtos.People.PeopleResult
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    var listPeople = listOf<PeopleResult>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.people_rcv, parent, false)

        return PeopleAdapter.PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bindItem(listPeople[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listPeople.size
    }



    class PeopleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(people: PeopleResult, itemListener: RecyclerViewItemListener, position: Int) {

            val imgFotoPessoa : ImageView = itemView.findViewById(R.id.imgFotoPessoa)
            val txtNomePessoa : TextView = itemView.findViewById(R.id.txtNomePessoa)

            if(!people.profile_path.isNullOrEmpty()){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + people.profile_path).into(imgFotoPessoa)
            }

            txtNomePessoa.text = people.name

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(itemView, position)
            }

        }

    }
}