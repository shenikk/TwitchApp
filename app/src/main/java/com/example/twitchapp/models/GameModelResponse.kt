package com.example.twitchapp.models

import com.google.gson.annotations.SerializedName

data class GameModelResponse(
    @SerializedName("data") val data: List<Game>,
    @SerializedName("pagination") val pagination: Cursor
)

data class Game(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("box_art_url") val image: String
)

data class Cursor(
    @SerializedName("cursor") val cursor: String
)
