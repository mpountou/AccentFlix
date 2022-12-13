package com.intelligent.accentflix.adapters.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.data.local.entities.Photo
import com.intelligent.accentflix.databinding.FavoriteMoviePhotoBinding

/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Photos for a selected favorite movie
 */
class FavMoviePhotosGridAdapter() :
    ListAdapter<Photo, FavMoviePhotosGridAdapter.FavMoviePhotoViewHolder>(DiffCallback) {

    /**
     * The FavMoviePhotoViewHolder constructor takes the binding variable from the associated
     * FavoriteMoviePhotoBinding, which nicely gives it access to the Photo information.
     */
    class FavMoviePhotoViewHolder(
        private var binding: FavoriteMoviePhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.moviePhoto = photo
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * Photo has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.photoId == newItem.photoId
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.image == newItem.image
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMoviePhotoViewHolder {
        return FavMoviePhotoViewHolder(
            FavoriteMoviePhotoBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FavMoviePhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}

