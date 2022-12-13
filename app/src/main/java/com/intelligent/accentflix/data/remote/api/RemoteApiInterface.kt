package com.intelligent.accentflix.data.remote.api

import com.intelligent.accentflix.models.movie.photos.MoviePhotos
import com.intelligent.accentflix.models.Movies
import com.intelligent.accentflix.models.movie.cast.Cast
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * In general we should hide credential stuff like api keys
 * at local files inside our machine.
 * but for simplicity i keep it here public
 */
const val apiKey = "k_77esm9hw"

/**
 * A public interface that exposes fetch methods from imdb-api.com
 */
interface RemoteApiInterface {
    /**
     * Returns a list of movies for 2022 year
     */
    @GET("AdvancedSearch/$apiKey?release_date=2022-01-01,2022-12-31")
    suspend fun getMovies(): Movies

    /**
     * Returns a list of movies based on term title that user inserts
     */
    @GET("AdvancedSearch/$apiKey?")
    suspend fun getMoviesByTitle(@Query("title") title: String): Movies

    /**
     * Returns a list of movie-related-images for a selected movie
     */
    @GET("Images/$apiKey/{id}/Short")
    suspend fun getMoviePhotos(@Path("id") id:String): MoviePhotos

    /**
     * Returns cast related-data for a selected movie
     */
    @GET("FullCast/$apiKey/{id}")
    suspend fun getMovieCast(@Path("id") id:String): Cast

}
