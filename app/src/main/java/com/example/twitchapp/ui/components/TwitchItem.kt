package com.example.twitchapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.twitchapp.models.Game

@Composable
fun TwitchItem(model: Game, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.LightGray
    ) {
        Row(modifier = Modifier.padding(8.dp).clickable { onClick.invoke() }) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}