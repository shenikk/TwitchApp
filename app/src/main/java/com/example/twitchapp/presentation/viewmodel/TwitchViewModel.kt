package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.data.DataRepository
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.data.VideoModel
import com.example.twitchapp.models.domain.GameEntity
import com.example.twitchapp.presentation.screens.LoadingResult
import com.example.twitchapp.presentation.screens.MainViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TwitchViewModel : ViewModel() {

    // TODO implement DI
    private val dataRepository = DataRepository()
    private val interactor = TwitchInteractor(dataRepository)

    private val handler = CoroutineExceptionHandler { _, _ ->
        _viewState.value = MainViewState(result = LoadingResult.Error)
    }

    private val accessToken: MutableState<String> = mutableStateOf("")
    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(handler) {
            val token = interactor.getToken().token
            accessToken.value = token

            interactor.getTopGames(token).collect {
                getVideos(token, it)
            }
        }
    }

    fun getVideosByTopic(gamesId: Long) {
        viewModelScope.launch {
            val games = viewState.value.games
            val videos = interactor.getVideos(accessToken.value, gamesId)

            updateViewState(games, videos.data)
        }
    }

    private suspend fun getVideos(token: String, games: List<GameEntity>) {
        val videos = interactor.getVideos(token, games.first().id.toLong())

        updateViewState(games, videos.data)
    }

    private fun updateViewState(games: List<GameEntity>, videos: List<VideoModel>) {
        _viewState.value = MainViewState(
            games = games,
            videos = videos,
            result = LoadingResult.Success
        )
    }
}
