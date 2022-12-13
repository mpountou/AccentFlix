package com.intelligent.accentflix.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.intelligent.accentflix.R
import com.intelligent.accentflix.databinding.FragmentHomeDetailsBinding
import com.intelligent.accentflix.ui.home.ApiStatus
import com.intelligent.accentflix.ui.home.HomeViewModel
import com.intelligent.accentflix.adapters.home.MovieActorGridAdapter
import com.intelligent.accentflix.adapters.home.MovieGenreGridAdapter
import com.intelligent.accentflix.adapters.home.MoviePhotosGridAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment shows the detailed information of a selected movie
 */
@AndroidEntryPoint
class HomeDetailFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.gridMoviePhotos.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridMoviePhotos.adapter = MoviePhotosGridAdapter()

        binding.gridMovieGenres.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridMovieGenres.adapter = MovieGenreGridAdapter()

        binding.gridMovieActors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        binding.gridMovieActors.adapter = MovieActorGridAdapter()

        viewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it == ApiStatus.ERROR){
                    Toast.makeText(context,resources.getString(R.string.error_api_request), Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

}