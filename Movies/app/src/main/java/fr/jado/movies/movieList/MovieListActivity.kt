package fr.jado.movies.movieList

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jado.movies.databinding.ActivityListMoviesBinding
import fr.jado.movies.login.TAG
import fr.jado.movies.movie.MovieAdapter
import fr.jado.movies.movie.Movies

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding


    lateinit var adapter: MovieAdapter
    private val model: MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MovieAdapter(listOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        model.moviesLiveData.observe(this){movies -> adapter.movies = movies
        adapter.notifyDataSetChanged()}
        model.loadMovies()

    }
//    private fun updateUi(state: MovieListViewState){
//        when(state){
//            is MovieListViewState.Succes -> {
//                Log.i(TAG, "updateUi: refresh recyclerView")}
//
//        is MovieListViewState.Error -> {
//
//        }
//    }

}