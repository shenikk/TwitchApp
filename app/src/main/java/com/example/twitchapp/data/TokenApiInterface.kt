package com.example.twitchapp.data

import com.example.twitchapp.models.data.TokenModelResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenApiInterface {

    @POST("oauth2/token")
    suspend fun getToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String
    ): Response<TokenModelResponse>
}