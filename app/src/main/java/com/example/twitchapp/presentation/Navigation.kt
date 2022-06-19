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
//            route = Screen.DetailScreen.route + "/{name}/{gameId}",
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                    defaultValue = "empty name"
//                    nullable = false
//                },
//                navArgument("gameId") {
//                    type = NavType.LongType
//                    defaultValue = 0L
//                    nullable = false
//                }
//            )
//        ) { backStackEntry ->
//            DetailScreen(
//                name = backStackEntry.arguments?.getString("name"),
//                gameId = backStackEntry.arguments?.getLong("gameId"),
//                viewModel = viewModel
//            )
//        }
    }
}
