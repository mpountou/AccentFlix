package com.intelligent.accentflix.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.databinding.MovieItemBinding
import com.intelligent.accentflix.models.movie.details.MovieWithDetails

/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Movies fetched from imdb-api
 */
class MoviesGridAdapter(val clickListener: MovieClickListener) :
    ListAdapter<MovieWithDetails, MoviesGridAdapter.MoviesViewHolder>(DiffCallback) {

    /**
     * The MoviesViewHolder constructor takes the binding variable from the associated
     * MovieItemBinding, which nicely gives it access to Movie item information.
     */
    class MoviesViewHolder(
        private var binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieWithDetails, clickListener:MovieClickListener) {
            movie.description = movie.description?.replace("(","")?.replace(")","")
            binding.photo = movie
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * Movie with details has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MovieWithDetails>() {
        override fun areItemsTheSame(oldItem: MovieWithDetails, newItem: MovieWithDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieWithDetails, newItem: MovieWithDetails): Boolean {
            return oldItem.image == newItem.image
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie,clickListener)
    }
}

class MovieClickListener(val clickListener: (movie: MovieWithDetails) -> Unit) {
    fun onClick(movie: MovieWithDetails) = clickListener(movie)
}
