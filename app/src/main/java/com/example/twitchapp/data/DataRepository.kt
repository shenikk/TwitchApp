package com.example.twitchapp.data

import com.example.twitchapp.models.data.TokenModelResponse
import com.example.twitchapp.models.data.VideoResponse
import com.example.twitchapp.models.domain.GameEntity

class DataRepository {

    suspend fun getToken(): TokenModelResponse =
        NetworkLayer.apiClient.getAccessToken().body() ?: throw GameException()

    suspend fun getTopGames(token: String): List<GameEntity> {
        val response = NetworkLayer.apiClient.getTopGames(token).body()
        return if (response != null) {
            GameConverter.convert(response)
        } else {
            throw GameException()
        }
    }

    suspend fun getVideos(accessToken: String, gamesId: Long): VideoResponse =
        NetworkLayer.apiClient.getVideos(accessToken, gamesId).body() ?: throw GameException()
}