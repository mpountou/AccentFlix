package com.intelligent.accentflix.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["actorId","movieId"])
data class Actor(
    val actorId: String,
    val movieId: String,
    val name: String?,
    val image: String?,
    val asCharacter: String?,
)
