package com.ak.popularmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ak.popularmovie.R
import com.ak.popularmovie.databinding.LayoutMovieItemBinding
import com.ak.popularmovie.model.Movie
import com.ak.popularmovie.utils.Constants

class MoviesAdapter(private val itemClickListener : (Movie?) -> Unit)
    : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutMovieItemBinding.inflate(layoutInflater,parent,false)
        return MovieViewHolder(binding)
    }

    inner class MovieViewHolder(private val binding : LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { itemClickListener.invoke(getItem(adapterPosition)) }
        }

        fun bind(movie : Movie) {
            binding.tvMovie.text = movie.title
            binding.imgPoster.load(Constants.IMAGE_BASE_URL+movie.posterPath) {
                crossfade(true)
                placeholder(R.drawable.poster_placeholder)
                error(R.drawable.poster_placeholder)
            }
        }
    }

    companion object {

        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}