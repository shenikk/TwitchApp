package com.example.twitchapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.twitchapp.models.Game
import com.example.twitchapp.presentation.GlideImage

@Composable
fun TwitchItem(model: Game, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.LightGray,
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick.invoke() }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp)
        ) {
            GlideImage(url = model.image).value?.let {
                Image(
                    bitmap = it,
                    contentDescription = null
                )
            }

            Text(
                text = model.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}