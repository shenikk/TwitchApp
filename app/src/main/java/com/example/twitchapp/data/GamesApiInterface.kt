package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GamesApiInterface {
    @GET("helix/games/top")
    suspend fun getGames(
        @Header("Authorization") authorization: String,
        @Header("Client-Id") clientId: String
    ) : Response<GameModelResponse>
}