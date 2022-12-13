package com.intelligent.accentflix.models.movie.details


import com.intelligent.accentflix.models.movie.details.genre.Genre

/**
 * api: https://imdb-api.com/API/AdvancedSearch/k_uhvi0sqq/?
 * response (MovieDetails):
 *  {
        "id":"tt0468569",
        "image":"https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_Ratio0.6837_AL_.jpg",
        "title":"The Dark Knight",
        "description":"(2008)",
        "runtimeStr":"152 min",
        "genres":"Action, Crime, Drama",
        "genreList":[...]
        "contentRating":"PG-13",
        "imDbRating":"9.0",
        "imDbRatingVotes":"2642644",
        "metacriticRating":"84",
        "plot":"When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
        "stars":"Christopher Nolan, Christian Bale, Heath Ledger, Aaron Eckhart, Michael Caine",
        "starList":[...]
}
 */

/**
 * Convert api json response to kotlin data class
 */
data class MovieWithDetails(
    val id: String,
    val image: String?,
    val title: String?,
    var description: String?,
    val runtimeStr: String?,
    val genres: String?,
    val genreList: List<Genre>?,
    val contentRating: String?,
    var imDbRating: String?,
    val imDbRatingVotes: String?,
    var metacriticRating: String?,
    val plot: String?,
)