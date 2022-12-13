package com.intelligent.accentflix.utils

import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.models.movie.details.MovieWithDetails

object MovieUtils {
    /**
     * Formats movie details data
     */
    fun formatMovie(movie: MovieWithDetails): MovieWithDetails {
        // example: (2022) -> 2022
        movie.description = movie.description?.replace("(", "")?.replace(")", "")
        // example: null -> -
        movie.metacriticRating = movie.metacriticRating ?: "-"
        
        return movie
    }
    fun convertMovieWithDetailsToMovie(movie: MovieWithDetails?): Movie {
        return Movie(movie!!.id ,movie.image,movie.title,movie.description,movie.runtimeStr,movie.genres,movie.contentRating,movie.imDbRating,movie.imDbRatingVotes,movie.metacriticRating,movie.plot)
    }

}