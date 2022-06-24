package com.example.twitchapp.domain

import com.example.twitchapp.data.DataRepository
import com.example.twitchapp.models.domain.GameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TwitchInteractor(
    private val repository: DataRepository
) {

    suspend fun getToken() = repository.getToken()

    suspend fun getTopGames(token: String): Flow<List<GameEntity>> = flow {
        emit(repository.getTopGames(token))
    }

    suspend fun getVideos(accessToken: String, gamesId: Long) =
        repository.getVideos(accessToken, gamesId)
}