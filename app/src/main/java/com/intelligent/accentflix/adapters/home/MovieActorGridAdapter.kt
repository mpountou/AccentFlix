package com.intelligent.accentflix.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intelligent.accentflix.databinding.MovieActorItemBinding
import com.intelligent.accentflix.models.movie.cast.actors.Actor


/**
 * This class implements a RecyclerView adapter which uses Data Binding to present
 * a list of Actors for a selected movie
 */
class MovieActorGridAdapter() :
    ListAdapter<Actor, MovieActorGridAdapter.MovieActorViewHolder>(DiffCallback) {

    /**
     * The MovieActorViewHolder constructor takes the binding variable from the associated
     * MovieActorItemBinding, which nicely gives it access to the Actor information.
     */
    class MovieActorViewHolder(
        private var binding: MovieActorItemBinding
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
            return oldItem.id == newItem.id
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
    ): MovieActorViewHolder {
        return MovieActorViewHolder(
            MovieActorItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MovieActorViewHolder, position: Int) {
        val actor = getItem(position)
        holder.bind(actor)
    }
}

