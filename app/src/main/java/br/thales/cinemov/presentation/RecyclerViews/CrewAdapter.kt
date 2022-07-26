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
import br.thales.cinemov.Application.Dtos.Movie.Cast
import br.thales.cinemov.Application.Dtos.Movie.Crew
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class CrewAdapter : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    var listCrew = listOf<Crew>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.credit_rcv, parent, false)

        return CrewAdapter.CrewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bindItem(listCrew[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listCrew.size
    }



    class CrewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(crew: Crew, itemListener: RecyclerViewItemListener, position: Int) {

            val imgCredit : ImageView = itemView.findViewById(R.id.imgCredit)
            val txtCreditName : TextView = itemView.findViewById(R.id.txtCreditName)
            val txtKnowFor : TextView = itemView.findViewById(R.id.txtKnowFor)
            val txtCharacterOrDepartament : TextView = itemView.findViewById(R.id.txtCharacterOrDepartament)


            if (crew.profile_path.isNullOrEmpty()){
                //Picasso.get().load(viewModel.usuario!!.fotoUri).transform(CropCircleTransformation()).into(binding.imgFotoUsuario)
            } else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + crew.profile_path).into(imgCredit)
            }

            if (crew.name != null){
                txtCreditName.text = crew.name
            }

            if (crew.known_for_department != null){
                txtKnowFor.text = crew.known_for_department
            }

            if (crew.department != null){
                txtCharacterOrDepartament.text = crew.department
            }

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(itemView, position)
            }


        }

    }

}