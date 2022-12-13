package com.intelligent.accentflix.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.databinding.MovieGenreItemBinding
import com.intelligent.accentflix.models.movie.details.genre.Genre


/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Genres for a selected movie
 */
class MovieGenreGridAdapter() :
    ListAdapter<Genre, MovieGenreGridAdapter.MovieGenreViewHolder>(DiffCallback) {

    /**
     * The MovieGenreViewHolder constructor takes the binding variable from the associated
     * MovieGenreItemBinding, which nicely gives it access to the Genre information.
     */
    class MovieGenreViewHolder(
        private var binding: MovieGenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genre = genre
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * Genre has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.value == newItem.value
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGenreViewHolder {
        return MovieGenreViewHolder(
            MovieGenreItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MovieGenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }
}

