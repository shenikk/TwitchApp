package com.example.twitchapp.presentation.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.twitchapp.models.data.VideoModel
import com.example.twitchapp.models.domain.GameEntity
import com.example.twitchapp.presentation.CircularInderterminateProgressBar
import com.example.twitchapp.presentation.viewmodel.TwitchViewModel
import com.example.twitchapp.ui.components.TwitchItem

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: TwitchViewModel = viewModel()

    // collectAsState() is important! Every time new values are sent to StateFlow,
    // the returned state will be updated
    val state = viewModel.viewState.collectAsState()

    when(state.value.result) {
        LoadingResult.Loading -> CircularInderterminateProgressBar(true)
        LoadingResult.Success -> CircularInderterminateProgressBar(false)
        LoadingResult.Error -> println("Something went wrong")
    }

    Column {
        ComposeList(listItems = state.value.games) {
            viewModel.getVideosByTopic(it)
        }

        Spacer(Modifier.height(8.dp))

        VideoComposeList(listItems = state.value.videos, navController)
    }
}

@Composable
fun ComposeList(
    listItems: List<GameEntity>,
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
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listItems) { item ->
            VideoItem(model = item,
                onClick = { openCustomTabs(context, item.url) }
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
            Text(
                text = model.duration,
                color = Color.DarkGray,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }
}

private fun openCustomTabs(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder().build()
    builder.launchUrl(context, Uri.parse(url))
}