//package com.example.twitchapp.presentation.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Card
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.example.twitchapp.models.VideoModel
//import com.example.twitchapp.presentation.viewmodel.TwitchViewModel
//
//@Composable
//fun DetailScreen(
//    name: String?,
//    gameId: Long?,
//    viewModel: TwitchViewModel
//) {
//    val context = LocalContext.current
//    LaunchedEffect(context) {
//        viewModel.accessToken.value?.let {
//            if (gameId != null) {
//                viewModel.getVideos(it, gameId)
//            }
//        }
//    }
//
//    val videos = viewModel.videos.value
//
//    Text(
//        text = name.orEmpty(),
//        color = Color.Black,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    )
//    if (videos != null) {
//        VideoComposeList(listItems = videos)
//    }
//}
//
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
//
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