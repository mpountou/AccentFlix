package com.intelligent.accentflix.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId","id"],tableName = "Genre")
data class Genre(
    val id: String,
    val movieId: String,
    val value: String
)