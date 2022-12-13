package com.intelligent.accentflix.ui.favorites.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.intelligent.accentflix.databinding.FragmentFavoriteMovieDetailBinding
import com.intelligent.accentflix.ui.favorites.FavoritesViewModel
import com.intelligent.accentflix.adapters.favorites.FavMovieActorGridAdapter
import com.intelligent.accentflix.adapters.favorites.FavMovieGenreGridAdapter
import com.intelligent.accentflix.adapters.favorites.FavMoviePhotosGridAdapter

/**
 * This Fragment shows the detailed information of a favorite movie
 */
class FavoriteDetailFragment : Fragment() {

    private val viewModel: FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFavoriteMovieDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.gridFavMoviePhotos.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridFavMoviePhotos.adapter = FavMoviePhotosGridAdapter()

        binding.gridFavMovieGenres.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridFavMovieGenres.adapter = FavMovieGenreGridAdapter()

        binding.gridFavMovieActors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridFavMovieActors.adapter = FavMovieActorGridAdapter()

        // Inflate the layout for this fragment
        return binding.root
    }
}