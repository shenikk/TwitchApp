package com.example.twitchapp.models.domain

import com.squareup.moshi.Json

data class GameEntity(
    val id: String,
    val name: String,
    val image: String
)