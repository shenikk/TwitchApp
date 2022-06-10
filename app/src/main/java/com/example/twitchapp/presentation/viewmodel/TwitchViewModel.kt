package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.Game
import kotlinx.coroutines.launch

class TwitchViewModel(
    private val interactor: TwitchInteractor
) : ViewModel() {

    val games: MutableState<List<Game>?> = mutableStateOf(listOf())
    val loading = mutableStateOf(false)

    fun getTopGames() {
        viewModelScope.launch {
            loading.value = true
            val token = interactor.getToken()?.token

            val response = token?.let { interactor.getTopGames(it) }
            games.value = response?.data
            loading.value = false
        }
    }
}