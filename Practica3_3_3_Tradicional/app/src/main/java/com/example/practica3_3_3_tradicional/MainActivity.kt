package com.example.practica3_3_3_tradicional

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.practica3_3_3_tradicional.ui.theme.Practica3_3_3_TradicionalTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val biometricAuthenticator = BiometricAuthenticator(this)

        setContent {
            Practica3_3_3_TradicionalTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val activity = LocalContext.current as FragmentActivity
                    var message by remember { mutableStateOf("") }

                    OutlinedButton(
                        modifier = Modifier
                            .size(250.dp, 60.dp),
                        onClick = {
                            biometricAuthenticator.promtBiometricAuth(
                                title = "Activar",
                                subTitle = "Utiliza la huella para activar",
                                negativeButtonText = "Cancelar",
                                fragmentActivity = activity,
                                onSuccess = {
                                    message = "Activado con éxito"
                                },
                                onFaild = {
                                    message = "Intentalo de nuevo"
                                },
                                onError = { _, error ->
                                    message = error
                                }
                            )
                        }) {
                        Text(
                            text = "Activar con huella",
                            color = Color.Blue,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = message, color = Color.Blue, fontSize = 24.sp)
                }
            }
        }
    }
}