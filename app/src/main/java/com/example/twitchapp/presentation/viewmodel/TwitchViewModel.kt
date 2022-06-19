package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.data.DataRepository
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.Game
import com.example.twitchapp.models.VideoModel
import kotlinx.coroutines.launch

class TwitchViewModel : ViewModel() {

    val games: MutableState<List<Game>?> = mutableStateOf(listOf())
    val loading = mutableStateOf(false)
    val accessToken: MutableState<String?> = mutableStateOf("")

    val videos: MutableState<List<VideoModel>?> = mutableStateOf(listOf())

    // TODO implement DI
    private val dataRepository = DataRepository()
    private val interactor = TwitchInteractor(dataRepository)

    init {
        viewModelScope.launch {
            loading.value = true
            val token = interactor.getToken()?.token
            accessToken.value = token

            // get top games
            val response = token?.let { interactor.getTopGames(it) }
            games.value = response?.data

            // get videos for the first top game
            if (token != null) {
                games.value?.first()?.id?.toLong()?.let { getVideos(token, it) }
            }

            loading.value = false
        }
    }

    fun getVideosByTopic(accessToken: String, gamesId: Long) {
        viewModelScope.launch {
            val videosResponse = interactor.getVideos(accessToken, gamesId)
            videos.value = videosResponse?.data
        }
    }

    private suspend fun getVideos(accessToken: String, gamesId: Long) {
        val videosResponse = interactor.getVideos(accessToken, gamesId)
        videos.value = videosResponse?.data
    }
}