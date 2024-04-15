package com.example.proy_comunitario.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.AppTitle
import com.example.proy_comunitario.ui.presentation.NavegacionInf
import com.example.proy_comunitario.ui.presentation.NotiCard
import com.example.proy_comunitario.ui.theme.HomeBackground
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun Notifications(navController : NavController){
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
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val scrollState = rememberScrollState()
            Surface (modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(HomeBackground)
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .verticalScroll(scrollState)
                ) {
                    // Titulo de la seccion
                    Text(text = "Notificaciones", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    ) {
                        NotiCard(R.drawable.pefil_police,"Policia Municipal","Alert")
                        Spacer(modifier = Modifier.height(5.dp))
                        NotiCard(R.drawable.perfil1,"Ana Perez","Foro")
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}