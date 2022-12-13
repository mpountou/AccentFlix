package com.intelligent.accentflix.data.remote.repositories



import com.intelligent.accentflix.data.remote.api.RemoteApiInterface
import com.intelligent.accentflix.models.Movies
import com.intelligent.accentflix.models.movie.cast.Cast
import com.intelligent.accentflix.models.movie.photos.MoviePhotos
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


/**
 * Implementation of Remote Imdb Repository that fetch data from imdb-api.com
 */
@ViewModelScoped
class RemoteMovieRepository @Inject constructor(
    private val imdbApiInterface: RemoteApiInterface,
) {

    /**
     * Returns a list of movies for 2022 year
     */
    suspend fun getMovies(): Movies = imdbApiInterface.getMovies()

    /**
     * Returns a list of movies based on term title that user inserts
     */
    suspend fun getMoviesByTitle(title: String): Movies = imdbApiInterface.getMoviesByTitle(title)

    /**
     * Returns a list of movie-related-images for a selected movie
     */
    suspend fun getMoviePhotos(movieId: String): MoviePhotos = imdbApiInterface.getMoviePhotos(movieId)

    /**
     * Returns cast related-data for a selected movie
     */
    suspend fun getMovieCast(movieId: String): Cast = imdbApiInterface.getMovieCast(movieId)

}