package com.example.music_player_app.ui.helper

import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import androidx.core.app.NotificationCompat
import com.example.music_player_app.R
import com.example.music_player_app.data.Song
import com.example.music_player_app.data.songsList

class SongHelper(private val context: Context) {
    companion object {
        private var mediaPlayer: MediaPlayer? = null
        private var currentPosition = 0
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "music_player_channel"
        private var currentSong: Song? = null

        fun playStream(context: Context, url: String) {
            currentSong = songsList.find { it.media == url }

            mediaPlayer?.let {
                if (it.isPlaying) {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                }
            }
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(url)
                prepareAsync()
            }
            mediaPlayer?.setOnPreparedListener {
                    mediaPlayer ->
                mediaPlayer.seekTo(currentPosition)
                mediaPlayer.start()
                showNotification(context, currentSong)
            }
        }

        fun pauseStream() {
            mediaPlayer?.let {
                currentPosition = it.currentPosition
                it.pause()
            }
        }

        fun stopStream() {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            currentPosition = 0
        }

        fun releasePlayer() {
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            currentPosition = 0
        }

        fun showNotification(context: Context, song: Song?) {
            song?.let {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.noti)
                    .setContentTitle(it.title)
                    .setContentText(it.artist)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                val notification = notificationBuilder.build()
                notificationManager.notify(NOTIFICATION_ID, notification)
            }
        }
    }
}
