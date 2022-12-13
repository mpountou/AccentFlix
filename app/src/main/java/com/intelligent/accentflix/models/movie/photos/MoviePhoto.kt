package com.intelligent.accentflix.models.movie.photos

/**
 * api: https://imdb-api.com/en/API/Images/k_uhvi0sqq/tt1375666
 * response (MoviePhotoItem):
 *  {
        "items":[
        {
        "title":"Leonardo DiCaprio and Ken Watanabe in Inception (2010)",
        "image":"https://m.media-amazon.com/images/M/MV5BMjIyNjk1OTgzNV5BMl5BanBnXkFtZTcwOTU0OTk1Mw@@._V1_Ratio1.5000_AL_.jpg"
        },...]
    }
 */

/**
 * Convert api json response to kotlin data class
 */
data class MoviePhoto(
    val title: String?,
    val image: String?
)