package com.example.twitchapp.data

import com.example.twitchapp.models.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VideosApiInterface {
    @GET("helix/videos")
    suspend fun getVideos(
        @Header("Authorization") authorization: String,
        @Header("Client-Id") clientId: String,
        @Query("game_id") gameId: Long
    ) : Response<VideoResponse>
}