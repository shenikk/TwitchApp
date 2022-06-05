package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GameApiInterface {

    @GET("helix/games/top")
    fun getGames(
        @Header("Authorization") authorization: String,
        @Header("Client_id") clientId: String,
    ) : Call<GameModelResponse>
}