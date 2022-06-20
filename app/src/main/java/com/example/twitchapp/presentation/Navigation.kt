package com.example.twitchapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twitchapp.presentation.screens.MainScreen

@Composable
fun Navigation() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = nav)
        }
//        composable(
//            route = Screen.DetailScreen.route + "/{video_url}",
//            arguments = listOf(
//                navArgument("video_url") {
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = false
//                }
//            )
//        ) { backStackEntry ->
//            DetailScreen(gameUrl = backStackEntry.arguments?.getString("video_url"))
//        }
    }
}
