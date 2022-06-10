package com.example.twitchapp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.Game
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TwitchViewModel(
    private val interactor: TwitchInteractor
) : ViewModel() {

    val games: MutableState<List<Game>?> = mutableStateOf(listOf())
    // TODO add errorLiveData and refreshLiveData

    fun getTopGames() {
//        viewModelScope.launch {
//            val token = async {
//                interactor.getToken()
//            }.await()
//
//            async {
//                val response = token?.token?.let { interactor.getTopGames(it) }
//                games.value = response?.data
//            }
//        }

        viewModelScope.launch {
            val token = interactor.getToken()

            val response = token?.token?.let { interactor.getTopGames(it) }
            games.value = response?.data
        }
    }
}