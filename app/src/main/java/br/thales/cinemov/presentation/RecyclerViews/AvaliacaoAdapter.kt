package br.thales.cinemov.presentation.RecyclerViews

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.Cast
import br.thales.cinemov.Domain.Entities.Avaliacao
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class AvaliacaoAdapter : RecyclerView.Adapter<AvaliacaoAdapter.AvaliacaoViewHolder>() {

    var listAvaliacao = listOf<Avaliacao>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvaliacaoAdapter.AvaliacaoViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.avaliacao_rcv, parent, false)

        return AvaliacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvaliacaoAdapter.AvaliacaoViewHolder, position: Int) {
        holder.bindItem(listAvaliacao[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listAvaliacao.size
    }

    class AvaliacaoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItem(avaliacao : Avaliacao, itemListener : RecyclerViewItemListener, position : Int){

            val imgUser : ImageView = itemView.findViewById(R.id.imgUser)
            val imgFilmeAvaliado : ImageView = itemView.findViewById(R.id.imgFilmeAvaliado)
            val txtUserNome : TextView = itemView.findViewById(R.id.txtUserNome)
            val txtTituloAvaliacao : TextView = itemView.findViewById(R.id.txtTitulo)
            val txtNota : TextView = itemView.findViewById(R.id.txtNota)
            val txtData : TextView = itemView.findViewById(R.id.txtDataAvaliacao)


            txtUserNome.setText(avaliacao.nomeAutor)
            txtTituloAvaliacao.setText(avaliacao.titulo)
            txtNota.setText(avaliacao.notaFilme.toString())
            txtData.text = avaliacao.data


            txtNota.setBackgroundColor(Color.BLACK)
            if (avaliacao.notaFilme!! >= 6.0){
                txtNota.setTextColor(Color.YELLOW)
            } else if(avaliacao.notaFilme!! >= 8.0){
                txtNota.setTextColor(Color.GREEN)
            } else {
                txtNota.setTextColor(Color.RED)
            }

            if (avaliacao.movieDto != null){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + avaliacao.movieDto?.poster_path).into(imgFilmeAvaliado)
            } else if(avaliacao.serieDto != null){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + avaliacao.serieDto?.poster_path).into(imgFilmeAvaliado)
            }

            //Picasso.get().load("https://image.tmdb.org/t/p/w500" + avaliacao.movieDto?.poster_path).into(imgFilmeAvaliado)

            Picasso.get().load(avaliacao.authorPhoto).transform(CropCircleTransformation()).into(imgUser)

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(it, position)
            }


        }

    }

}