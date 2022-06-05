package com.example.twitchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twitchapp.models.TestModel
import com.example.twitchapp.presentation.TwitchItem
import com.example.twitchapp.ui.theme.TwitchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitchAppTheme {
                ComposeList(listItems = listOf(
                    TestModel("1 line"),
                    TestModel("2 lines"),
                    TestModel("3 lines")
                ))
            }
        }
    }
}

@Composable
fun ComposeList(
    listItems: List<TestModel>
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listItems) { item -> 
            TwitchItem(model = item)
        }
    }
}
