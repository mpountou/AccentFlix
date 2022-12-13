package com.intelligent.accentflix.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.accentflix.data.local.entities.Actor
import com.intelligent.accentflix.data.local.entities.Genre
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.data.local.entities.Photo
import com.intelligent.accentflix.data.local.repositories.LocalMovieRepository
import com.intelligent.accentflix.utils.MovieUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This FavoritesViewModel class is attached to FavoritesFragment & FavoriteDetailFragment.
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: LocalMovieRepository) : ViewModel() {

    //  internal StateFlow can be mutable to handle the list of favorite movies
    private val _favoriteMovies: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the list of favorite movies
    val favoriteMovies: StateFlow<List<Movie>?> = _favoriteMovies

    //  internal StateFlow can be mutable to handle the selected favorite movie
    private val _favoriteMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the selected favorite movie
    val favoriteMovie: StateFlow<Movie?> = _favoriteMovie

    //  internal StateFlow can be mutable to handle the list of favorite movie photos
    private val _selectedMoviePhotos: MutableStateFlow<List<Photo>?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the list of favorite movie photos
    val selectedMoviePhotos: StateFlow<List<Photo>?> = _selectedMoviePhotos

    //  internal StateFlow can be mutable to handle the list of favorite movie genres
    private val _selectedMovieGenres: MutableStateFlow<List<Genre>?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the list of favorite movie genres
    val selectedMovieGenres: StateFlow<List<Genre>?> = _selectedMovieGenres

    //  internal StateFlow can be mutable to handle the list of favorite movie actors
    private val _selectedMovieActors: MutableStateFlow<List<Actor>?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the list of favorite movie actors
    val selectedMovieActors: StateFlow<List<Actor>?> = _selectedMovieActors

    /**
     * Query local sql database when viewModel first created
     */
    init {
        getFavoriteMoviesList()
    }

    /**
     * Gets Movies information from local sql database and update the data
     */
    private fun getFavoriteMoviesList() {
        viewModelScope.launch {
            repository.getAllFavoriteMovies().collect {
                _favoriteMovies.value = it
            }
        }
    }

    /**
     * On Favorite-Fragment screen when user click a movie this function is invoked
     */
    fun onFavoriteMovieClicked(movie: Movie) {
        _favoriteMovie.value = movie
        getSelectedMoviePhotos(movie.id)
        getSelectedMovieGenres(movie.id)
        getSelectedMovieActors(movie.id)
    }

    /**
     * For the favorite movie that user selected, fetch all photos from the local db
     */
    private fun getSelectedMoviePhotos(movieId:String) {
        viewModelScope.launch {
            repository.getSelectedMoviePhotos(movieId).collect {
                _selectedMoviePhotos.value = it
            }
        }
    }

    /**
     * For the favorite movie that user selected, fetch all genres from the local db
     */
    private fun getSelectedMovieGenres(movieId:String) {
        viewModelScope.launch {
            repository.getSelectedMovieGenres(movieId).collect {
                _selectedMovieGenres.value = it
            }
        }
    }

    /**
     * For the favorite movie that user selected, fetch all actors from the local db
     */
    private fun getSelectedMovieActors(movieId:String) {
        viewModelScope.launch {
            repository.getSelectedMovieActors(movieId).collect {
                _selectedMovieActors.value = it
            }
        }
    }

    /**
     * Delete selected Movie information from local database (favorites list)
     */
    fun deleteMovieFromFavorites(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovie(movie)
            deleteGenreMovie(movie)
            deleteMoviePhoto(movie)
            deleteMovieActor(movie)
        }
    }

    /**
     * Delete movie information from Movie table
     */
    private suspend fun deleteMovie(movie: Movie) {
        repository.deleteMovie(movie)
    }

    /**
     * Delete genre information of movie from Genre table
     */
    private suspend fun deleteGenreMovie(movie: Movie) {
        repository.deleteGenresByMovieId(movie.id)
    }

    /**
     * Delete photo information of movie from Photo table
     */
    private suspend fun deleteMoviePhoto(movie: Movie) {
        repository.deletePhotosByMovieId(movie.id)
    }

    /**
     * Delete all actors information of movie by movie Id
     */
    private suspend fun deleteMovieActor(movie: Movie) {
        repository.deleteActorsByMovieId(movie.id)
    }
}