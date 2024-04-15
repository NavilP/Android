package com.example.music_player_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music_player_app.data.songsList
import com.example.music_player_app.ui.helper.SongHelper
import com.example.music_player_app.ui.screens.HomeScreen
import com.example.music_player_app.ui.theme.Music_Player_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SongHelper(applicationContext)
        setContent {
            Music_Player_AppTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text(text = "Reproductor de música", color = Color.White)},
                        backgroundColor = Color.Black
                    )
                }){ innerPadding ->
                    HomeScreen(songsList = songsList, innerPadding, context = applicationContext)
                }

            }
        }
    }
}
