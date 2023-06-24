package com.example.movieappsolera.ui.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappsolera.R
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.utils.GlobalConstants.poster_path

class MoviesAdapter (private val movies: List<MovieModel>, private val itemRecipeClickListener: OnRecipeClickListener):
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    interface OnRecipeClickListener {
        fun onRecipeClick(movies: MovieModel, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_recipe, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.movie_name.text = movie.title
        holder.movie_date.text = movie.release_date
        Glide.with(holder.movie_image)
            .load(poster_path + movie.image)
            .centerCrop()
            .into(holder.movie_image);


        holder.itemView.setOnClickListener {
            itemRecipeClickListener.onRecipeClick(movie, position)
        }

    }

    override fun getItemCount(): Int = movies.size

    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val movie_name : TextView = itemView.findViewById(R.id.movie_name)
        val movie_date : TextView = itemView.findViewById(R.id.movie_fecha)
        val movie_image : ImageView = itemView.findViewById(R.id.movie_image)

    }


}