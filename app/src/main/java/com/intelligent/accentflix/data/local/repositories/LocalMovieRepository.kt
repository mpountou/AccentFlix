package com.intelligent.accentflix.data.local.repositories

import com.intelligent.accentflix.data.local.dao.ActorDao
import com.intelligent.accentflix.data.local.dao.GenreDao
import com.intelligent.accentflix.data.local.dao.MovieDao
import com.intelligent.accentflix.data.local.dao.PhotoDao
import com.intelligent.accentflix.data.local.entities.Actor
import com.intelligent.accentflix.data.local.entities.Genre
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.data.local.entities.Photo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * MovieRepository class is on the top of data layer
 * and communicates with data layer to fetch the data needed
 */
@ViewModelScoped
class LocalMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val photoDao: PhotoDao,
    private val actorDao: ActorDao,
) {

    /**
     * Get all favorite movies
     */
    fun getAllFavoriteMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    /**
     * Get a selected favorite movie
     */
    fun getSelectedMovie(movieId: String): Flow<Movie> = movieDao.getSelectedMovie(movieId)

    /**
     * Add a favorite movie
     */
    suspend fun addMovie(movie: Movie) = movieDao.addMovie(movie)

    /**
     * Delete a past favorite movie
     */
    suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    /**
     * Get all genres for a selected favorite movie
     */
    fun getSelectedMovieGenres(movieId: String): Flow<List<Genre>> = genreDao.getSelectedMovieGenres(movieId)

    /**
     * Add a genre for a selected favorite movie
     */
    suspend fun addGenreMovie(genre: Genre) = genreDao.addMovieGenre(genre)

    /**
     * Delete a genre for a past favorite movie
     */
    suspend fun deleteGenreMovie(genre: Genre) = genreDao.deleteMovieGenre(genre)

    /**
     * Delete all genre for a selected favorite movie by movieId
     */
    suspend fun deleteGenresByMovieId(movieId: String) = genreDao.deleteGenreByMovieId(movieId)

    /**
     * Get all photos of a favorite movie
     */
    fun getSelectedMoviePhotos(movieId: String): Flow<List<Photo>> = photoDao.getMoviePhotos(movieId)

    /**
     * Add a movie photo for a selected favorite movie
     */
    suspend fun addMoviePhoto(photo: Photo) = photoDao.addMoviePhoto(photo)

    /**
     * Delete a movie photo for a selected past favorite movie
     */
    suspend fun deleteMoviePhoto(photo: Photo) = photoDao.deleteMoviePhoto(photo)

    /**
     * Delete all photos for a selected favorite movie by movieId
     */
    suspend fun deletePhotosByMovieId(movieId: String) = photoDao.deletePhotosByMovieId(movieId)

    /**
     * Get all actors for a selected favorite movie
     */
    fun getSelectedMovieActors(movieId: String): Flow<List<Actor>> = actorDao.getMovieActors(movieId)

    /**
     * Add an actor for a selected favorite movie
     */
    suspend fun addMovieActor(actor: Actor) = actorDao.addMovieActor(actor)

    /**
     * Delete an actor for a past favorite movie
     */
    suspend fun deleteMovieActor(actor: Actor) = actorDao.deleteMovieActor(actor)

    /**
     * Delete all actors for a selected favorite movie by movieId
     */
    suspend fun deleteActorsByMovieId(movieId: String) = actorDao.deleteActorsByMovieId(movieId)

}