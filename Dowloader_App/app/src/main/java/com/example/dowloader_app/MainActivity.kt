package com.example.dowloader_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.dowloader_app.ui.theme.Dowloader_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val downloader = AndroidDownloader(this)
        //downloader.downloadFile("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tuexperto.com%2F2022%2F01%2F27%2Fmejores-paginas-fondos-pantalla-4k%2F&psig=AOvVaw14R2seZ0DDRKTM20XQ6X1r&ust=1710629281746000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCMiqh-es94QDFQAAAAAdAAAAABAE")

        super.onCreate(savedInstanceState)
        setContent {
            Dowloader_AppTheme {
                Column( modifier = Modifier.fillMaxSize(),
                    verticalArrangement= Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    TextField(
                        value = text,
                        onValueChange = { newText ->
                            downloader.downloadFile(newText.text)
                        },
                        placeholder = { Text("URL de la imagen a descargar") }
                    )

                    //Text(text = "Descargando. Revisa las notificaciones.")
                }
            }
        }

    }
}