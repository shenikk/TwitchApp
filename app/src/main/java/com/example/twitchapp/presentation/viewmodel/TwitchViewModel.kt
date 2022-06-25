package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.data.DataRepository
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.data.VideoModel
import com.example.twitchapp.models.domain.GameEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TwitchViewModel : ViewModel() {

    private val _games: MutableState<List<GameEntity>?> = mutableStateOf(emptyList())
    val games: State<List<GameEntity>?>
        get() = _games

    val loading = mutableStateOf(false)
    val accessToken: MutableState<String?> = mutableStateOf("")

    val videos: MutableState<List<VideoModel>?> = mutableStateOf(listOf())

    // TODO implement DI
    private val dataRepository = DataRepository()
    private val interactor = TwitchInteractor(dataRepository)

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Oh no")
        loading.value = false
    }

    init {
        viewModelScope.launch(handler) {
            loading.value = true
            val token = interactor.getToken().token
            accessToken.value = token

            // get top games
            getTopGames(token)

            // get videos for the first top game
            _games.value?.first()?.id?.toLong()?.let { getVideos(token, it) }

            loading.value = false
        }
    }

    fun getVideosByTopic(accessToken: String, gamesId: Long) {
        viewModelScope.launch {
            val videosResponse = interactor.getVideos(accessToken, gamesId)
            videos.value = videosResponse.data
        }
    }

    private suspend fun getTopGames(token: String?) {
        token?.let { interactor.getTopGames(it) }?.collect {
            _games.value = it
        }
    }

    private suspend fun getVideos(accessToken: String, gamesId: Long) {
        val videosResponse = interactor.getVideos(accessToken, gamesId)
        videos.value = videosResponse.data
    }
}