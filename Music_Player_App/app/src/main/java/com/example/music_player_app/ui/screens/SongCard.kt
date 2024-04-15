package com.example.music_player_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.music_player_app.data.Song
import com.example.music_player_app.data.songsList

@Composable
fun SongCard(song: Song, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
        backgroundColor = Color.Black,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            AsyncImage(
                model = song.imageUrl,
                contentDescription = "Song thumbnail",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = song.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = song.artist,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(90.dp))
            Text(
                text = song.duration,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SongCardPreview() {
    SongCard(
        songsList[0],
        onClick = {}
    )
}