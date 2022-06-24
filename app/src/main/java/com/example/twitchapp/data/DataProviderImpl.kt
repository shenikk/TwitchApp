package com.example.twitchapp.data

import com.example.twitchapp.models.data.GameModelResponse
import com.example.twitchapp.models.data.TokenModelResponse
import com.example.twitchapp.models.data.VideoResponse
import retrofit2.Response

class DataProviderImpl(
    private val api: TokenApiInterface,
    private val gameApi: GamesApiInterface,
    private val videoApi: VideosApiInterface
) : DataProvider {

    override suspend fun getAccessToken(): Response<TokenModelResponse> {
        return api.getToken(
            CLIENT_ID,
            CLIENT_SECRET,
            GRANT_TYPE
        )
    }

    override suspend fun getTopGames(accessToken: String): Response<GameModelResponse> {
        return gameApi.getGames(
            "Bearer $accessToken",
            CLIENT_ID
        )
    }

    override suspend fun getVideos(accessToken: String, gamesId: Long): Response<VideoResponse> {
        return  videoApi.getVideos(
            "Bearer $accessToken",
            CLIENT_ID,
            gamesId
        )
    }

    companion object {
        // TODO add real values
        private const val CLIENT_ID = ""
        private const val CLIENT_SECRET = ""
        private const val GRANT_TYPE = ""
    }
}