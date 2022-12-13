package com.intelligent.accentflix.adapters.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.databinding.FavoriteMovieItemBinding

/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of favorite Movies fetched from imdb-api
 */
class FavoriteMoviesGridAdapter(val clickListener: FavoriteMovieClickListener, val deleteFavoriteMovieClickListener: DeleteFavoriteMovieClickListener) :
    ListAdapter<Movie, FavoriteMoviesGridAdapter.FavoriteMoviesViewHolder>(DiffCallback) {

    /**
     * The FavoriteMoviesViewHolder constructor takes the binding variable from the associated
     * FavoriteMovieItemBinding, which nicely gives it access to Movie item information.
     */
    class FavoriteMoviesViewHolder(
        private var binding: FavoriteMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: Movie, clickListener: FavoriteMovieClickListener, deleteClickListener: DeleteFavoriteMovieClickListener) {
            binding.favoriteMovie = favoriteMovie
            binding.clickListener = clickListener
            binding.deleteClickListener = deleteClickListener
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * favorite Movie with details has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.image == newItem.image
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesViewHolder {
        return FavoriteMoviesViewHolder(
            FavoriteMovieItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie,clickListener,deleteFavoriteMovieClickListener)
    }
}
class FavoriteMovieClickListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}
class DeleteFavoriteMovieClickListener(val deleteClickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = deleteClickListener(movie)
}
