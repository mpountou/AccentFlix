package com.intelligent.accentflix.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intelligent.accentflix.data.local.entities.Actor
import kotlinx.coroutines.flow.Flow

/**
 * ActorDao interface - CRUD operations for actors of a favorite movie
 */
@Dao
interface ActorDao {

    /**
     * Get all actors for a selected favorite movie
     */
    @Query("SELECT * FROM Actor where movieId = :movieId")
    fun getMovieActors(movieId: String): Flow<List<Actor>>

    /**
     * Add an actor for a selected favorite movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieActor(actor: Actor)

    /**
     * Delete an actor for a past favorite movie
     */
    @Delete
    suspend fun deleteMovieActor(actor: Actor)

    /**
     * Delete all movie actors for a selected past favorite movie by movie id
     */
    @Query("DELETE FROM Actor WHERE movieId = :movieId")
    suspend fun deleteActorsByMovieId(movieId: String)

}