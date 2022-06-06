package com.example.twitchapp.domain

import com.example.twitchapp.data.DataRepository

class TwitchInteractor(
    private val repository: DataRepository
) {

    suspend fun getToken() = repository.getToken()

    suspend fun getTopGames(token: String) = repository.getTopGames(token)
}