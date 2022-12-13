package com.intelligent.accentflix.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intelligent.accentflix.data.local.entities.Genre
import kotlinx.coroutines.flow.Flow

/**
 * GenreDao interface - CRUD operations for Genres of a favorite movie
 */
@Dao
interface GenreDao {

    /**
     * Get all genres for a selected favorite movie
     */
    @Query("SELECT * FROM Genre WHERE id = :id")
    fun getSelectedMovieGenres(id:String): Flow<List<Genre>>

    /**
     * Add a genre for a selected favorite movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenre(genre: Genre)

    /**
     * Delete a genre for a past favorite movie
     */
    @Delete
    suspend fun deleteMovieGenre(genre: Genre)

    /**
     * Delete all genre given movieId
     */
    @Query("DELETE FROM Genre WHERE movieId = :movieId")
    suspend fun deleteGenreByMovieId(movieId: String)
}