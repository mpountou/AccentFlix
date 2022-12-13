package com.intelligent.accentflix.models.movie.photos

/**
 * api: https://imdb-api.com/en/API/Images/k_uhvi0sqq/tt1375666
 * response (MoviePhotos):
 *  {
        "imDbId":"tt1375666",
        "title":"Inception",
        "fullTitle":"Inception (2010)",
        "type":"Movie",
        "year":"2010",
        "items":[
        {
        "title":"Leonardo DiCaprio and Ken Watanabe in Inception (2010)",
        "image":"https://m.media-amazon.com/images/M/MV5BMjIyNjk1OTgzNV5BMl5BanBnXkFtZTcwOTU0OTk1Mw@@._V1_Ratio1.5000_AL_.jpg"
        },...]
 */

/**
 * Convert api json response to kotlin data class
 */
data class MoviePhotos(
    val imDbId: String?,
    val title: String?,
    val fullTitle: String?,
    val type: String?,
    val year: String?,
    val items: List<MoviePhoto>
)