package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import com.example.twitchapp.models.TokenModelResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.coroutineContext

class DataRepository {

//    suspend fun getTopGames(): GameModelResponse? {
//        val token = CoroutineScope().async {
//            NetworkLayer.apiClient.getAccessToken()
//        }.await()
////        val token = NetworkLayer.apiClient.getAccessToken()
//
//
//        return if (token.isSuccessful) {
//            token.body()?.token?.let { NetworkLayer.apiClient.getTopGames(it) }?.body()
//        } else {
//            null
//        }
//    }

    suspend fun getToken(): TokenModelResponse? {
        return NetworkLayer.apiClient.getAccessToken().body()
    }

    suspend fun getTopGames(token: String): GameModelResponse? {
       return NetworkLayer.apiClient.getTopGames(token).body()
    }
}