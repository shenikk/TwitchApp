package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import com.example.twitchapp.models.TokenModelResponse
import retrofit2.Response

interface DataProvider {

    suspend fun getAccessToken(): Response<TokenModelResponse>

    suspend fun getTopGames(accessToken: String): Response<GameModelResponse>
}