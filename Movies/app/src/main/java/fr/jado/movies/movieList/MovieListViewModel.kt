package fr.jado.movies.movieList

import android.view.textclassifier.ConversationActions.Message
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jado.movies.R
import fr.jado.movies.movie.Genre
import fr.jado.movies.movie.Movies

abstract class MovieListViewState{
    data class  Succes(val movies: List<Movies>)
    data class  Error(val errorMessage: String)
    class Loading(): MovieListViewState()
}

class MovieListViewModel: ViewModel() {

    val moviesLiveData = MutableLiveData<List<Movies>>()

   private val movies = listOf(
        Movies(1, "Avatar", Genre.sf, "2022", R.drawable.avatar),
        Movies(2, "Black panther", Genre.sf, "2021", R.drawable.black_panther),
        Movies(3, "Sausage party", Genre.comedy, "2022", R.drawable.sausage_party),
        Movies(4, "Avatar", Genre.sf, "2022", R.drawable.avatar),
        Movies(2, "Black panther", Genre.sf, "2021", R.drawable.black_panther),
        Movies(3, "Sausage party", Genre.comedy, "2022", R.drawable.sausage_party),
        Movies(1, "Avatar", Genre.sf, "2022", R.drawable.avatar),
        Movies(2, "Black panther", Genre.sf, "2021", R.drawable.black_panther),
        Movies(3, "Sausage party", Genre.comedy, "2022", R.drawable.sausage_party),
        Movies(1, "Avatar", Genre.sf, "2022", R.drawable.avatar),
        Movies(2, "Black panther", Genre.sf, "2021", R.drawable.black_panther),
        Movies(3, "Sausage party", Genre.comedy, "2022", R.drawable.sausage_party),

        )

     fun loadMovies(){
         moviesLiveData.value = movies
     // stateLiveData.value = MovieListViewState.Loading()
    }
}