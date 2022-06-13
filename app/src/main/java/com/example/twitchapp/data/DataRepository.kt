package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import com.example.twitchapp.models.TokenModelResponse
import com.example.twitchapp.models.VideoResponse

class DataRepository {

    suspend fun getToken(): TokenModelResponse? = NetworkLayer.apiClient.getAccessToken().body()

    suspend fun getTopGames(token: String): GameModelResponse? =
        NetworkLayer.apiClient.getTopGames(token).body()

    suspend fun getVideos(accessToken: String, gamesId: Long): VideoResponse? =
        NetworkLayer.apiClient.getVideos(accessToken, gamesId).body()
}