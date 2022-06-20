package com.example.twitchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.twitchapp.presentation.Navigation
import com.example.twitchapp.ui.theme.TwitchAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TwitchAppTheme {
                Navigation()
            }
        }
    }
}