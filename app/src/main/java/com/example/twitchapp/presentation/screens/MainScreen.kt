package com.example.twitchapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.twitchapp.models.Game
import com.example.twitchapp.models.VideoModel
import com.example.twitchapp.presentation.CircularInderterminateProgressBar
import com.example.twitchapp.presentation.Screen
import com.example.twitchapp.presentation.viewmodel.TwitchViewModel
import com.example.twitchapp.ui.components.TwitchItem
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: TwitchViewModel = viewModel()
    val token = viewModel.accessToken.value
    val games = viewModel.games.value
    val loading = viewModel.loading.value
    val videos = viewModel.videos.value

    CircularInderterminateProgressBar(loading)

    Column {
        if (games != null) {
            ComposeList(listItems = games) {
                if (token != null) {
                    viewModel.getVideosByTopic(token, it)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        if (videos != null) {
            VideoComposeList(listItems = videos, navController)
        }
    }
}

@Composable
fun ComposeList(
    listItems: List<Game>,
    updateVideos: (Long) -> Unit
) {
    LazyRow {
        items(listItems) { item ->
            TwitchItem(
                model = item,
                onClick = {
                    updateVideos.invoke(item.id.toLong())
                }
            )
        }
    }
}

@Composable
fun VideoComposeList(
    listItems: List<VideoModel>,
    navController: NavController
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listItems) { item ->
            VideoItem(model = item,
                onClick = {
                    val encodedUrl = URLEncoder.encode(item.url, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.DetailScreen.withArgs(encodedUrl))
                }
            )
        }
    }
}

@Composable
fun VideoItem(model: VideoModel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.LightGray,
        modifier = Modifier.clickable { onClick.invoke() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = model.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp)
            )
            Text(
                text = model.userName,
                color = Color.DarkGray,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }
}