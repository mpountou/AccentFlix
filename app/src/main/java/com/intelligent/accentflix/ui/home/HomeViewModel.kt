package com.intelligent.accentflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.accentflix.data.local.entities.Actor
import com.intelligent.accentflix.data.local.entities.Genre
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.data.local.entities.Photo
import com.intelligent.accentflix.data.local.repositories.LocalMovieRepository
import com.intelligent.accentflix.data.remote.repositories.RemoteMovieRepository
import com.intelligent.accentflix.models.movie.cast.Cast
import com.intelligent.accentflix.models.movie.details.MovieWithDetails
import com.intelligent.accentflix.models.movie.photos.MoviePhoto
import com.intelligent.accentflix.utils.MovieUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

/**
 * This HomeViewModel class is attached to HomeFragment & HomeDetailFragment.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalMovieRepository,
    private val remoteRepository: RemoteMovieRepository
) : ViewModel() {

    //  internal LiveData can be mutable to stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    // external LiveData should be immutable to just pass the request status
    val status: LiveData<ApiStatus> = _status

    //  internal LiveData can be mutable to update the list of Movies
    private val _movies = MutableLiveData<List<MovieWithDetails>>()
    // external LiveData should be immutable to just pass the list of movies
    val movies: LiveData<List<MovieWithDetails>> = _movies

    //  internal LiveData can be mutable to update the selected Movie
    private val _movie = MutableLiveData<MovieWithDetails>()
    // external LiveData should be immutable to just pass the selected Movie
    val movie: LiveData<MovieWithDetails> = _movie

    //  internal LiveData can be mutable to update the selected Movie
    private val _moviePhotos = MutableLiveData<List<MoviePhoto>>()
    // external LiveData should be immutable to just pass the selected Movie
    val moviePhotos: LiveData<List<MoviePhoto>> = _moviePhotos

    //  internal StateFlow can be mutable to update the favorite Movie
    private val _favoriteMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    // external StateFlow should be immutable to just pass the favorite Movie
    val favoriteMovie: StateFlow<Movie?> = _favoriteMovie

    //  internal LiveData can be mutable to update the selected Movie
    private val _movieCast = MutableLiveData<Cast>()
    // external LiveData should be immutable to just pass the selected Movie
    val movieCast: LiveData<Cast> = _movieCast

    // internal LiveData can be mutable to update if user made a search event
    private val _madeSearch = MutableLiveData<Boolean>(false)
    // external LiveData should be immutable to just pass if user made a search event
    val madeSearch: LiveData<Boolean> = _madeSearch

    // internal LiveData can be mutable to update user inserted term
    private val _searchTerm = MutableLiveData<String>("")
    // external LiveData should be immutable to just pass user inserted term
    val searchTerm: LiveData<String> = _searchTerm

    /**
     * Call Imdb API when viewModel first created
     */
    init {
        getMoviesList()
    }

    /**
     * Gets Movies information from the Imdb API and update the data
     */
    private fun getMoviesList() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            _movies.value = listOf()
            try {
                _madeSearch.value = false
                _searchTerm.value = ""
                _movies.value = remoteRepository.getMovies().results
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _movies.value = listOf()
            }
        }
    }

    /**
     * Gets Movies information by Title from the Imdb API and update the data
     */
    fun getMoviesByTitle(title: String) {
        viewModelScope.launch {
            _movies.value = listOf()
            _status.value = ApiStatus.LOADING
            try {
                _madeSearch.value = true
                _searchTerm.value = title
                _movies.value = remoteRepository.getMoviesByTitle(title).results
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _movies.value = listOf()
            }
        }
    }

    /**
     * On Home Fragment screen when user click a movie this function is invoked
     */
    fun onMovieClicked(movie: MovieWithDetails) {
        _movie.value = MovieUtils.formatMovie(movie)
        getFavoriteMovie(movie.id)
        getMoviePhotos(movie.id)
        getMovieCast(movie.id)
    }

    /**
     * Gets Movies information by Title from the Imdb API and update the data
     */
    private fun getMoviePhotos(id: String) {
        viewModelScope.launch {
            _moviePhotos.value = listOf()
            _status.value = ApiStatus.LOADING
            try {
                _moviePhotos.value = remoteRepository.getMoviePhotos(id).items
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _moviePhotos.value = listOf()
            }
        }
    }

    /**
     * Gets Movies information by Title from the Imdb API and update the data
     */
    private fun getMovieCast(id: String) {
        viewModelScope.launch {
            _movieCast.value = Cast()
            _status.value = ApiStatus.LOADING
            try {
                _movieCast.value = remoteRepository.getMovieCast(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _movieCast.value = Cast()
            }
        }
    }

    /**
     * Gets Favorite Movie information by Id from local database
     */
    private fun getFavoriteMovie(movieId: String) {
        viewModelScope.launch {
            localRepository.getSelectedMovie(movieId).collect {
                _favoriteMovie.value = it
            }
        }
    }

    /**
     * Add selected Movie information to local database (favorites list)
     */
    fun addMovieToFavorites() {
        val movie: Movie = MovieUtils.convertMovieWithDetailsToMovie(_movie.value)
        if (_status.value != ApiStatus.ERROR) {
            viewModelScope.launch(Dispatchers.IO) {
                addMovie(movie)
                addGenreMovie(movie)
                addMoviePhoto(movie)
                addMovieActor(movie)
            }
        }
    }

    /**
     * Add movie information to Movie table
     */
    private suspend fun addMovie(movie: Movie) {
        localRepository.addMovie(movie)
    }

    /**
     * Add genre information of movie to Genre table
     */
    private suspend fun addGenreMovie(movie: Movie) {
        _movie.value?.genreList?.forEach {
            localRepository.addGenreMovie(Genre(it.key, movie.id, it.value))
        }
    }

    /**
     * Add photos information of movie to Photos table
     */
    private suspend fun addMoviePhoto(movie: Movie) {
        _moviePhotos.value?.forEachIndexed { index, photo ->
            localRepository.addMoviePhoto(Photo(movie.id, index, photo.image))
        }
    }

    /**
     * Add actors information of movie to Actor table
     */
    private suspend fun addMovieActor(movie: Movie) {
        _movieCast.value?.actors?.forEach { actor ->
            localRepository.addMovieActor(
                Actor(
                    actor.id!!,
                    movie.id,
                    actor.name,
                    actor.image,
                    actor.asCharacter
                )
            )
        }
    }

    /**
     * Delete selected Movie information from local database (favorites list)
     */
    fun deleteMovieFromFavorites() {
        val movie: Movie = MovieUtils.convertMovieWithDetailsToMovie(_movie.value)
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteMovie.value = null
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
        localRepository.deleteMovie(movie)
    }

    /**
     * Delete genre information of movie from Genre table
     */
    private suspend fun deleteGenreMovie(movie: Movie) {
        _movie.value?.genreList?.forEach {
            localRepository.deleteGenreMovie(Genre(it.key, movie.id, it.value))
        }
    }

    /**
     * Delete photo information of movie from Photo table
     */
    private suspend fun deleteMoviePhoto(movie: Movie) {
        _moviePhotos.value?.forEachIndexed { index, photo ->
            localRepository.deleteMoviePhoto(Photo(movie.id, index, photo.image))
        }
    }

    /**
     * Delete actor information of movie from Actor table
     */
    private suspend fun deleteMovieActor(movie: Movie) {
        _movieCast.value?.actors?.forEach { actor ->
            localRepository.deleteMovieActor(
                Actor(
                    actor.id!!,
                    movie.id,
                    actor.name,
                    actor.image,
                    actor.asCharacter
                )
            )
        }
    }

    /**
     * If user searched a movie and then press back button or exit search
     * then reset the search
     */
    fun resetSearch(){
        getMoviesList()
    }
}