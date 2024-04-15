package com.example.music_player_app.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.music_player_app.data.Song

@Composable
fun HomeScreen(songsList: List<Song>, innerContentPadding: PaddingValues, context: Context) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(top = 20.dp)
    ) {
        SongsList(songsList = songsList, onSongSelected = { song ->
            selectedSong = song
        })
        selectedSong?.let {
            MediaPlayerCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Transparent),
                song = it,
                context = context
            )
        }
    }
}