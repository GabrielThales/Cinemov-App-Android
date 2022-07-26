package br.thales.cinemov.presentation.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.thales.cinemov.Application.Dtos.Movie.Cast
import br.thales.cinemov.R
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    var listCast = listOf<Cast>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener : RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener : RecyclerViewItemListener){
        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.credit_rcv, parent, false)

        return CastAdapter.CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindItem(listCast[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listCast.size
    }



    class CastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(cast: Cast, itemListener: RecyclerViewItemListener, position: Int) {

            val imgCredit : ImageView = itemView.findViewById(R.id.imgCredit)
            val txtCreditName : TextView = itemView.findViewById(R.id.txtCreditName)
            val txtKnowFor : TextView = itemView.findViewById(R.id.txtKnowFor)
            val txtCharacterOrDepartament : TextView = itemView.findViewById(R.id.txtCharacterOrDepartament)


            if (cast.profile_path.isNullOrEmpty()){
                //Picasso.get().load(viewModel.usuario!!.fotoUri).transform(CropCircleTransformation()).into(binding.imgFotoUsuario)
            } else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + cast.profile_path).into(imgCredit)
            }

            if (cast.name != null){
                txtCreditName.text = cast.name
            }

            if (cast.known_for_department != null){
                txtKnowFor.text = cast.known_for_department
            }

            if (cast.character != null){
                txtCharacterOrDepartament.text = cast.character
            }

            itemView.setOnClickListener {
                itemListener.recyclerViewItemClicked(itemView, position)
            }


        }

    }

}