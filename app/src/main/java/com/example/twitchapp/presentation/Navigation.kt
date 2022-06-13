package com.example.twitchapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.twitchapp.ComposeList
import com.example.twitchapp.models.VideoModel
import com.example.twitchapp.presentation.viewmodel.TwitchViewModel

@Composable
fun Navigation() {
    val nav = rememberNavController()
    // FIXME do it in a proper way
    val viewModel: TwitchViewModel = viewModel()

    NavHost(navController = nav, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = nav, viewModel = viewModel)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}/{gameId}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "empty name"
                    nullable = false
                },
                navArgument("gameId") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) { backStackEntry ->
            DetailScreen(
                name = backStackEntry.arguments?.getString("name"),
                gameId = backStackEntry.arguments?.getLong("gameId"),
                viewModel = viewModel
            )
        }
    }
}

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

@Composable
fun DetailScreen(
    name: String?,
    gameId: Long?,
    viewModel: TwitchViewModel
) {
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.accessToken.value?.let {
            if (gameId != null) {
                viewModel.getVideos(it, gameId)
            }
        }
    }

    val videos = viewModel.videos.value

    Text(
        text = name.orEmpty(),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    if (videos != null) {
        VideoComposeList(listItems = videos)
    }
}

@Composable
fun VideoComposeList(
    listItems: List<VideoModel>
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listItems) { item ->
            VideoItem(model = item)
        }
    }
}

@Composable
fun VideoItem(model: VideoModel) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.LightGray
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = model.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}