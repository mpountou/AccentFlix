package com.intelligent.accentflix.models.movie.details.genre

/**
 * api: https://imdb-api.com/API/AdvancedSearch/k_uhvi0sqq/?
 * response (Genre):
 *  "genreList":[
 *  {
        "key":"Action",
        "value":"Action"
    },
        ]
 */

/**
 * Convert api json response to kotlin data class
 */
data class Genre(
    val key: String,
    val value: String
)

