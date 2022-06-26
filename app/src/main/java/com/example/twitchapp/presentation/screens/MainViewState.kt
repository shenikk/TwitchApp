package com.example.twitchapp.presentation.screens

import com.example.twitchapp.models.data.VideoModel
import com.example.twitchapp.models.domain.GameEntity

data class MainViewState(
    val games: List<GameEntity> = emptyList(),
    val videos: List<VideoModel> = emptyList(),
    val result: LoadingResult = LoadingResult.Loading
)
