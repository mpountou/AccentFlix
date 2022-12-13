package com.intelligent.accentflix.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: String,
    val image: String?,
    val title: String?,
    var description: String?,
    val runtimeStr: String?,
    val genres: String?,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingVotes: String?,
    val metacriticRating: String?,
    val plot: String?,
)