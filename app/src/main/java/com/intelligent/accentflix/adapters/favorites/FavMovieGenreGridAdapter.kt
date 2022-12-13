package com.intelligent.accentflix.adapters.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.data.local.entities.Genre
import com.intelligent.accentflix.databinding.FavoriteMovieGenreItemBinding



/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Genres for a selected favorite movie
 */
class FavMovieGenreGridAdapter() :
    ListAdapter<Genre, FavMovieGenreGridAdapter.FavoriteMovieGenreViewHolder>(DiffCallback) {

    /**
     * The FavMovieGenreGridAdapter constructor takes the binding variable from the associated
     * FavoriteMovieGenreItemBinding, which nicely gives it access to the Genre information.
     */
    class FavoriteMovieGenreViewHolder(
        private var binding: FavoriteMovieGenreItemBinding
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
            return oldItem.id == newItem.id
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
    ): FavoriteMovieGenreViewHolder {
        return FavoriteMovieGenreViewHolder(
            FavoriteMovieGenreItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FavoriteMovieGenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }
}

