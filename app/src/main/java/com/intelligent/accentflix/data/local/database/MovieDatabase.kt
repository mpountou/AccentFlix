package com.intelligent.accentflix.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intelligent.accentflix.data.local.dao.ActorDao
import com.intelligent.accentflix.data.local.dao.GenreDao
import com.intelligent.accentflix.data.local.dao.MovieDao
import com.intelligent.accentflix.data.local.dao.PhotoDao
import com.intelligent.accentflix.data.local.entities.Actor
import com.intelligent.accentflix.data.local.entities.Genre
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.data.local.entities.Photo

/**
 * MovieDatabase class is a data layer for movie local db that handles all CRUD actions
 */
@Database(entities = [Movie::class, Genre::class,Photo::class,Actor::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun movieGenreDao() : GenreDao
    abstract fun moviePhotoDao() : PhotoDao
    abstract fun movieActorDao() : ActorDao
}