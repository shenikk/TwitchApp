package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import com.example.twitchapp.models.TokenModelResponse

class DataRepository {

    suspend fun getToken(): TokenModelResponse? = NetworkLayer.apiClient.getAccessToken().body()

    suspend fun getTopGames(token: String): GameModelResponse? =
        NetworkLayer.apiClient.getTopGames(token).body()
}