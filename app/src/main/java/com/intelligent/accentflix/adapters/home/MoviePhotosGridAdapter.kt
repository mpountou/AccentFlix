package com.intelligent.accentflix.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.databinding.MoviePhotoItemBinding

import com.intelligent.accentflix.models.movie.photos.MoviePhoto

/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Photos for a selected movie
 */
class MoviePhotosGridAdapter() :
    ListAdapter<MoviePhoto, MoviePhotosGridAdapter.MoviePhotosViewHolder>(DiffCallback) {

    /**
     * The MoviePhotosViewHolder constructor takes the binding variable from the associated
     * MoviePhotoItemBinding, which nicely gives it access to the Photo information.
     */
    class MoviePhotosViewHolder(
        private var binding: MoviePhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: MoviePhoto) {
            binding.moviePhoto = photo
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * Photo has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MoviePhoto>() {
        override fun areItemsTheSame(oldItem: MoviePhoto, newItem: MoviePhoto): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: MoviePhoto, newItem: MoviePhoto): Boolean {
            return oldItem.image == newItem.image
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviePhotosViewHolder {
        return MoviePhotosViewHolder(
            MoviePhotoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MoviePhotosViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}

