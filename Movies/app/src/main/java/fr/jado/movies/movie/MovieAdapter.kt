package fr.jado.movies.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jado.movies.databinding.ItemMovieBinding

class MovieAdapter( var movies: List<Movies>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = movies[position]
        holder.binding.posterImageView.setImageResource(movies.coverID)
        holder.binding.titleTextView.text = movies.name
        holder.binding.genderTextView.text = movies.genre.toString()
        holder.binding.dateTextView.text = movies.datesortie
    }

    override fun getItemCount(): Int = movies.size


}

