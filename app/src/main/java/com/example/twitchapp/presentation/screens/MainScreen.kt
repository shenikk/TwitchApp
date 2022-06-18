package com.example.twitchapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.twitchapp.ComposeList
import com.example.twitchapp.presentation.CircularInderterminateProgressBar
import com.example.twitchapp.presentation.viewmodel.TwitchViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: TwitchViewModel) {
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.getTopGames()
    }
    val games = viewModel.games.value
    val loading = viewModel.loading.value

    CircularInderterminateProgressBar(loading)
    if (games != null) {
        ComposeList(listItems = games, navController = navController)
    }
}