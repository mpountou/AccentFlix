package com.intelligent.accentflix.adapters.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.data.local.entities.Actor
import com.intelligent.accentflix.databinding.FavoriteMovieActorItemBinding


/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Actors for a selected favorite movie
 */
class FavMovieActorGridAdapter() :
    ListAdapter<Actor, FavMovieActorGridAdapter.FavMovieActorViewHolder>(DiffCallback) {

    /**
     * The FavMovieActorViewHolder constructor takes the binding variable from the associated
     * FavoriteMovieActorItemBinding, which nicely gives it access to the Actor information.
     */
    class FavMovieActorViewHolder(
        private var binding: FavoriteMovieActorItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
            binding.actor = actor
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the List of
     * Actor has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.actorId == newItem.actorId
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.image == newItem.image
        }
    }

    /**
     * Create new RecyclerView item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMovieActorViewHolder {
        return FavMovieActorViewHolder(
            FavoriteMovieActorItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FavMovieActorViewHolder, position: Int) {
        val actor = getItem(position)
        holder.bind(actor)
    }
}

