package com.example.twitchapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twitchapp.ComposeList
import com.example.twitchapp.models.Game

@Composable
fun Navigation(games: List<Game>?, loading: Boolean) {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = nav, games = games, loading = loading)
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(name = "jdjdj")
        }
    }
}

@Composable
fun MainScreen(navController: NavController, games: List<Game>?, loading: Boolean) {
    CircularInderterminateProgressBar(loading)
    if (games != null) {
        ComposeList(listItems = games, navController = navController)
    }
}

@Composable
fun DetailScreen(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}