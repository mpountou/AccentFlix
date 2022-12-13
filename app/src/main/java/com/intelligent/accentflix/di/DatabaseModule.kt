package com.intelligent.accentflix.di

import android.content.Context
import androidx.room.Room
import com.intelligent.accentflix.data.local.dao.ActorDao
import com.intelligent.accentflix.data.local.dao.GenreDao
import com.intelligent.accentflix.data.local.dao.MovieDao
import com.intelligent.accentflix.data.local.dao.PhotoDao
import com.intelligent.accentflix.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Room database
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "MovieDatabase"
    ).build()

    // MovieDao interface
    @Singleton
    @Provides
    fun provideDao(database: MovieDatabase): MovieDao = database.movieDao()

    // GenreDao interface
    @Singleton
    @Provides
    fun provideGenreDao(database: MovieDatabase): GenreDao = database.movieGenreDao()

    // PhotoDao interface
    @Singleton
    @Provides
    fun providePhotoDao(database: MovieDatabase): PhotoDao = database.moviePhotoDao()

    // ActorDao interface
    @Singleton
    @Provides
    fun provideActorDao(database: MovieDatabase): ActorDao = database.movieActorDao()

}