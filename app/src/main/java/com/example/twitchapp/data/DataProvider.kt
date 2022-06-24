package com.example.twitchapp.data

import com.example.twitchapp.models.data.GameModelResponse
import com.example.twitchapp.models.data.TokenModelResponse
import com.example.twitchapp.models.data.VideoResponse
import retrofit2.Response

interface DataProvider {

    suspend fun getAccessToken(): Response<TokenModelResponse>

    suspend fun getTopGames(accessToken: String): Response<GameModelResponse>

    suspend fun getVideos(accessToken: String, gamesId: Long): Response<VideoResponse>
}