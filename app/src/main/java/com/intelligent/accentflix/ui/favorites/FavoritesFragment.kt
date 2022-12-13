package com.intelligent.accentflix.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.intelligent.accentflix.R
import com.intelligent.accentflix.adapters.favorites.DeleteFavoriteMovieClickListener
import com.intelligent.accentflix.adapters.favorites.FavoriteMovieClickListener
import com.intelligent.accentflix.adapters.favorites.FavoriteMoviesGridAdapter
import com.intelligent.accentflix.databinding.FragmentFavoritesBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by activityViewModels()

    private var _binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        _binding!!.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        _binding!!.viewModel = viewModel

        // Sets the adapter of the moviesGrid RecyclerView
        _binding!!.moviesGrid.adapter =
            FavoriteMoviesGridAdapter(FavoriteMovieClickListener { movie ->
                viewModel.onFavoriteMovieClicked(movie)
                findNavController()
                    .navigate(R.id.action_favoritesFragment_to_favoritesFragmentDetail)
            }, DeleteFavoriteMovieClickListener { movie ->
                viewModel.deleteMovieFromFavorites(movie)
            })
        return _binding!!.root
    }
}