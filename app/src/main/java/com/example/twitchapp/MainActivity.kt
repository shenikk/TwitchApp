package com.example.twitchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitchapp.data.DataRepository
import com.example.twitchapp.domain.TwitchInteractor
import com.example.twitchapp.models.Game
import com.example.twitchapp.presentation.CircularInderterminateProgressBar
import com.example.twitchapp.presentation.TwitchItem
import com.example.twitchapp.presentation.viewmodel.TwitchViewModel
import com.example.twitchapp.ui.theme.TwitchAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TwitchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        viewModel.getTopGames()

        setContent {
            TwitchAppTheme {
                val games = viewModel.games.value
                val loading = viewModel.loading.value

                CircularInderterminateProgressBar(loading)
                if (games != null) {
                    ComposeList(listItems = games)
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val dataRepository = DataRepository()
                val interactor = TwitchInteractor(dataRepository)

                return TwitchViewModel(interactor) as T
            }
        }).get(TwitchViewModel::class.java)
    }
}

@Composable
fun ComposeList(
    listItems: List<Game>
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
