package com.example.twitchapp.data

import com.example.twitchapp.models.GameModelResponse
import com.example.twitchapp.models.TokenModelResponse

interface DataProvider {

    fun getAccessToken(): TokenModelResponse

    fun getTopGames(): GameModelResponse
}