package com.example.proy_comunitario.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.AppTitle
import com.example.proy_comunitario.ui.presentation.NavegacionInf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // Barra de usuario y titulo de la app
            AppTitle(
                photoResId = R.drawable.your_profile,
                title = "Conexión Comunitaria",
                iconResId = R.string.notification_content_desc,
                navController = navController
            )
        },
        bottomBar = {
            val selectedDestination = remember { mutableStateOf("Foros") }
            NavegacionInf(
                destination = selectedDestination,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ForosScreen(navController = navController)
        }
    }
}