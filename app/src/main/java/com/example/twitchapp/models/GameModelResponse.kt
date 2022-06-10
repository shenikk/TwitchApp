package com.example.twitchapp.models

import com.squareup.moshi.Json

data class GameModelResponse(
    @Json(name = "data") val data: List<Game>,
    @Json(name = "pagination") val pagination: Cursor
)

data class Game(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "box_art_url") val image: String
)

data class Cursor(
    @Json(name = "cursor") val cursor: String
)
