package com.intelligent.accentflix

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.intelligent.accentflix.data.local.entities.Movie
import com.intelligent.accentflix.data.local.entities.Photo
import com.intelligent.accentflix.models.movie.cast.actors.Actor
import com.intelligent.accentflix.models.movie.details.genre.Genre
import com.intelligent.accentflix.models.movie.details.MovieWithDetails
import com.intelligent.accentflix.models.movie.photos.MoviePhoto
import com.intelligent.accentflix.adapters.favorites.FavMovieActorGridAdapter
import com.intelligent.accentflix.adapters.favorites.FavMovieGenreGridAdapter
import com.intelligent.accentflix.adapters.favorites.FavMoviePhotosGridAdapter
import com.intelligent.accentflix.adapters.favorites.FavoriteMoviesGridAdapter
import com.intelligent.accentflix.ui.home.ApiStatus
import com.intelligent.accentflix.adapters.home.MovieActorGridAdapter
import com.intelligent.accentflix.adapters.home.MovieGenreGridAdapter
import com.intelligent.accentflix.adapters.home.MoviePhotosGridAdapter
import com.intelligent.accentflix.adapters.home.MoviesGridAdapter

/**
 * Updates the list of movies in HomeFragment
 */
@BindingAdapter("moviesData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieWithDetails>?) {
    val adapter = recyclerView.adapter as MoviesGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of photos shown in HomeDetailFragment
 */
@BindingAdapter("photoData")
fun bindPhotosRecyclerView(recyclerView: RecyclerView, data: List<MoviePhoto>?) {
    val adapter = recyclerView.adapter as MoviePhotosGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of genre shown in HomeDetailFragment
 */
@BindingAdapter("genreData")
fun bindGenresRecyclerView(recyclerView: RecyclerView, data: List<Genre>?) {
    val adapter = recyclerView.adapter as MovieGenreGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of actors shown in HomeDetailFragment
 */
@BindingAdapter("actorData")
fun bindCastRecyclerView(recyclerView: RecyclerView, data: List<Actor>?) {
    val adapter = recyclerView.adapter as MovieActorGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of movies shown in FavoriteFragment
 */
@BindingAdapter("favMoviesData")
fun bindFavoriteRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as FavoriteMoviesGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of photos shown in FavoriteDetailFragment
 */
@BindingAdapter("favPhotoData")
fun bindFavPhotoRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as FavMoviePhotosGridAdapter
    adapter.submitList(data)
}

/**
 * Updates the list of genres shown in FavoriteDetailFragment
 */
@BindingAdapter("favGenreData")
fun bindFavGenreRecyclerView(
    recyclerView: RecyclerView,
    data: List<com.intelligent.accentflix.data.local.entities.Genre>?
) {
    val adapter = recyclerView.adapter as FavMovieGenreGridAdapter
    adapter.submitList(data)
}


/**
 * Updates the list of actors shown in FavoriteDetailFragment
 */
@BindingAdapter("favActorData")
fun bindFavActorRecyclerView(
    recyclerView: RecyclerView,
    data: List<com.intelligent.accentflix.data.local.entities.Actor>?
) {
    val adapter = recyclerView.adapter as FavMovieActorGridAdapter
    adapter.submitList(data)
}

/**
 * Use Coil library to load an image by URL into an ImageView
 */
@BindingAdapter("loadImageUrl")
fun bindImageUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            scale(Scale.FILL)
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Display the ApiStatus of the network request in a Lottie Animation
 */
@BindingAdapter("bindStatus")
fun bindStatusLottie(lottieAnimation: LottieAnimationView, status: ApiStatus) {
    when (status) {
        ApiStatus.LOADING -> {
            lottieAnimation.visibility = View.VISIBLE
            lottieAnimation.setAnimation(R.raw.loading)
            lottieAnimation.repeatMode = LottieDrawable.RESTART
            lottieAnimation.playAnimation()
        }
        ApiStatus.ERROR -> {
            lottieAnimation.visibility = View.VISIBLE
            lottieAnimation.setAnimation(R.raw.connection_error)
            lottieAnimation.repeatMode = LottieDrawable.RESTART
            lottieAnimation.playAnimation()
        }
        ApiStatus.DONE -> {
            lottieAnimation.visibility = View.GONE
        }
    }
}