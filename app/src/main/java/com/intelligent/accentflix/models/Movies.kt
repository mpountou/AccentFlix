package com.intelligent.accentflix.models

import com.intelligent.accentflix.models.movie.details.MovieWithDetails

/**
 * api: https://imdb-api.com/API/AdvancedSearch/k_uhvi0sqq/?
 * response (Movies):
 *  {
    "queryString":"?title_type=feature,tv_movie,tv_special,documentary,short,video&num_votes=5000,&genres=action&runtime=100,&sort=user_rating,desc",
    "results":[...]
    }
 */

/**
 * Convert api json response to kotlin data class
 */
data class Movies(
    val queryString: String,
    val results: List<MovieWithDetails>
)