package com.intelligent.accentflix.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intelligent.accentflix.data.local.entities.Photo
import kotlinx.coroutines.flow.Flow

/**
 * PhotoDao interface - CRUD operations for photos of favorite movies
 */
@Dao
interface PhotoDao {

    /**
     * Get all photos of a favorite movie
     */
    @Query("SELECT * FROM Photo WHERE movieId = :id")
    fun getMoviePhotos(id:String) : Flow<List<Photo>>

    /**
     * Add a movie photo for a selected favorite movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoviePhoto(photo: Photo)

    /**
     * Delete a movie photo for a selected past favorite movie
     */
    @Delete
    suspend fun deleteMoviePhoto(photo: Photo)

    /**
     * Delete a movie photo for a selected past favorite movie
     */
    @Query("DELETE FROM Photo WHERE movieId = :movieId")
    suspend fun deletePhotosByMovieId(movieId: String)

}