package com.intelligent.accentflix.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId","photoId"])
data class Photo(
    val movieId: String,
    val photoId: Int,
    val image: String?
)