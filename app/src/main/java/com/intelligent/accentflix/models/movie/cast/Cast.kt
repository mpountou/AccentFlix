package com.intelligent.accentflix.models.movie.cast

import com.intelligent.accentflix.models.movie.cast.actors.Actor

/**
 * api: https://imdb-api.com/en/API/FullCast/k_uhvi0sqq/tt1375666
 * response (Cast):
 *  {
        "imDbId":"tt1375666",
        "title":"Inception",
        "fullTitle":"Inception (2010)",
        "type":"Movie",
        "year":"2010",
        "actors":[
            {
            "id":"nm0000138",
            "image":"https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_Ratio0.7273_AL_.jpg",
            "name":"Leonardo DiCaprio",
            "asCharacter":"Cobb"
            },...]
    }
 */

/**
 * Convert api json response to kotlin data class
 */
data class Cast (
    val imDbId: String? = null,
    val title: String? = null,
    val fullTitle: String?= null,
    val type: String?= null,
    val year: String?= null,
    val actors: List<Actor>?= listOf()
)