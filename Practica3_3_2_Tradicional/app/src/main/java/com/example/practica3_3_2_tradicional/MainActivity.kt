package com.example.practica3_3_2_tradicional

import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.practica3_3_2_tradicional.ui.theme.Practica3_3_2_TradicionalTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Practica3_3_2_TradicionalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Auth()
                }
            }
        }

        setupAuth()
    }

    private var canAuthenticate = false
    private val promptInfo: BiometricPrompt.PromptInfo? = null

    private fun setupAuth() {
        if (BiometricManager.from(this)
                .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS
        )
            canAuthenticate = true
        var promtInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación biométrica")
            .setSubtitle("Autenticación utilizando el sensor biométrico")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
    }

    private fun authenticate(auth: (auth: Boolean) -> Unit, promptInfo: BiometricPrompt.PromptInfo) {
        if (canAuthenticate) {
            BiometricPrompt(
                this,
                ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promtInfo)
        } else {
            auth(true)
        }

    }

    @Composable
    fun Auth() {
        var auth by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(if (auth) Color.White else Color.Black)
                .fillMaxSize()
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                if (auth) "Ya te has autentiado" else "Por favor utiliza la huella para autenticarte",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (auth) Color.Black else Color.White
            )
            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    if (auth) {
                        auth = false
                    } else{
                        authenticate {
                            auth = it
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(if (auth) "Cerrar Sesión" else "Autenticar")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Practica3_3_2_TradicionalTheme {
            Auth()
        }
    }
}