package br.thales.cinemov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.thales.cinemov.Application.Dtos.Movie.MovieDto
import br.thales.cinemov.Application.Dtos.People.PeopleDetailsDto
import br.thales.cinemov.Application.Dtos.People.PeopleResult
import br.thales.cinemov.Application.Interfaces.PeopleServiceListener
import br.thales.cinemov.databinding.ActivityAvaliacaoDetailsBinding
import br.thales.cinemov.databinding.ActivityPeopleDetailsBinding
import br.thales.cinemov.presentation.RecyclerViews.Listeners.RecyclerViewItemListener
import com.squareup.picasso.Picasso

class PeopleDetailsActivity : AppCompatActivity(), PeopleServiceListener, RecyclerViewItemListener {
    private lateinit var viewModel: PeopleDetailsViewModel
    private lateinit var binding: ActivityPeopleDetailsBinding
    private lateinit var people : PeopleResult
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_people_details)
        binding = ActivityPeopleDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(PeopleDetailsViewModel::class.java)
        viewModel.peopleService.setEventListener(this)
        viewModel.knowForAdapter.setRecyclerViewItemListener(this)

        if (intent != null){

            val people = intent.getParcelableExtra<PeopleResult>("people")
            if (people != null && people.id != 0){
                this.people = people
                viewModel.peopleService.getPeopleDetails(people.id!!)

            }
        }

        val linerLayout = LinearLayoutManager(this)
        linerLayout.orientation = LinearLayoutManager.HORIZONTAL

        if(!this.people.known_for.isNullOrEmpty()){
            viewModel.knowForAdapter.listKnowFor = people.known_for!!

            binding.rcvPeopleDetailsKnowFor.adapter = viewModel.knowForAdapter
            binding.rcvPeopleDetailsKnowFor.layoutManager = linerLayout

        }



    }

    override fun requisicaoTerminou(t: PeopleDetailsDto?) {
        if(t != null){
            binding.txtPeopleDetailsName.text = t.name

            if (t.birthday !=null){
                binding.txtPeopleDetailsBirthday.text = t.birthday
            } else {
                binding.txtPeopleDetailsBirthday.text = "Sem Informação"
            }

            if (!t.place_of_birth.isNullOrEmpty()){
                binding.txtPeopleDetailsPlaceBirth.text = t.place_of_birth
            } else {
                binding.txtPeopleDetailsPlaceBirth.text = "Sem Informação"
            }

            if (!t.known_for_department.isNullOrEmpty()){
                binding.txtPeopleDetailsKnowForDepartment.text = t.known_for_department
            }

            if (!t.profile_path.isNullOrEmpty()){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + t.profile_path).into(binding.imgPeopleDetailsProfile)
            } else {
                Picasso.get().load(R.drawable.ic_launcher_foreground).into(binding.imgPeopleDetailsProfile)
            }

            if(!t.biography.isNullOrEmpty()){
                binding.txtPeopleDetailsBiography.text = t.biography
            }

        }
    }

    override fun falhaRequisicao(t: String?) {
        Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
    }

    override fun recyclerViewItemClicked(view: View, id: Int) {
        val knowFor = viewModel.knowForAdapter.listKnowFor[id]
        if (knowFor.media_type == "movie"){
            val intent = Intent(this, MovieDetailActivity::class.java)
            val movieDto = MovieDto(knowFor.adult, knowFor.backdrop_path, knowFor.genre_ids, knowFor.id, knowFor.original_title, knowFor.original_title, knowFor.overview, knowFor.vote_average, knowFor.poster_path, knowFor.release_date, knowFor.title, knowFor.video, knowFor.vote_average, knowFor.vote_count)
            intent.putExtra("movie", movieDto)
            startActivity(intent)

        }

    }
}