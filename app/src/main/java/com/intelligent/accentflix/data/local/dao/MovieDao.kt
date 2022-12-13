package com.intelligent.accentflix.data.local.dao

import androidx.room.*
import com.intelligent.accentflix.data.local.entities.Movie
import kotlinx.coroutines.flow.Flow

/**
 * MovieDao interface - CRUD operations for all favorite movies
 */
@Dao
interface MovieDao {

    /**
     * Get all favorite movies
     */
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): Flow<List<Movie>>

    /**
     * Get a favorite movie
     */
    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getSelectedMovie(id : String): Flow<Movie>

    /**
     * Add a favorite movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    /**
     * Delete a past favorite movie
     */
    @Delete
    suspend fun deleteMovie(movie: Movie)

}