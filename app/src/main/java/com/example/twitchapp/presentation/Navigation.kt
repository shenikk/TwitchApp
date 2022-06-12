package com.example.twitchapp.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.twitchapp.ComposeList
import com.example.twitchapp.models.Game

@Composable
fun Navigation(games: List<Game>?, loading: Boolean) {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = nav, games = games, loading = loading)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "empty name"
                nullable = false
            })
        ) { entry ->
            DetailScreen(
                name = entry.arguments?.getString("name")
            )
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
fun DetailScreen(name: String?) {
    Text(
        text = name.orEmpty(),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

//@Composable
//fun VideoComposeList(
//    listItems: List<VideoModel>
//) {
//    LazyColumn(
//        contentPadding = PaddingValues(all = 8.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(listItems) { item ->
//            VideoItem(model = item)
//        }
//    }
//}

//@Composable
//fun VideoItem(model: VideoModel) {
//    Card(
//        shape = RoundedCornerShape(6.dp),
//        backgroundColor = Color.LightGray
//    ) {
//        Row(modifier = Modifier.padding(8.dp)) {
//            Text(
//                text = model.title,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//        }
//    }
//}