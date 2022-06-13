package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.Game
import com.example.twitchapp.models.VideoModel
import kotlinx.coroutines.launch

class TwitchViewModel(
    private val interactor: TwitchInteractor
) : ViewModel() {

    val games: MutableState<List<Game>?> = mutableStateOf(listOf())
    val loading = mutableStateOf(false)
    val accessToken: MutableState<String?> = mutableStateOf("")

    val videos: MutableState<List<VideoModel>?> = mutableStateOf(listOf())

    fun getTopGames() {
        viewModelScope.launch {
            loading.value = true
            val token = interactor.getToken()?.token
            accessToken.value = token

            val response = token?.let { interactor.getTopGames(it) }
            games.value = response?.data
            loading.value = false
        }
    }

    fun getVideos(accessToken: String, gamesId: Long) {
        viewModelScope.launch {
            val videosResponse = interactor.getVideos(accessToken, gamesId)
            videos.value = videosResponse?.data
        }
    }
}