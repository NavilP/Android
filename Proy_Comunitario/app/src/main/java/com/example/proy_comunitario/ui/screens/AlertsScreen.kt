package com.example.proy_comunitario.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.AlertCard
import com.example.proy_comunitario.ui.presentation.AppTitle
import com.example.proy_comunitario.ui.presentation.NavegacionInf
import com.example.proy_comunitario.ui.theme.HomeBackground
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun AlertScreen(navController: NavController) {
    val activity = LocalContext.current as? ComponentActivity
    val backDispatcher = activity?.onBackPressedDispatcher ?: return
    val homeScreenRoute = "foros"

    BackHandler(enabled = true) {
        navController.popBackStack(homeScreenRoute, inclusive = false)
    }

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
            val selectedDestination = remember { mutableStateOf("Alertas") }
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "Alertas",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    // Publicaciones de alertas
                    AlertCard(
                        propietario = "Policia Municipal",
                        time = "10:00 AM",
                        perfil = R.drawable.pefil_police,
                        mesage = "Se reporta una fuga de agua en C. Falsa, col. Falsa, se recomienda tener precauciones ya que se cerran de forma temporal las calles.",
                        //imagesList: List<Int>,
                        numLikes = "10"
                    )

                    AlertCard(
                        propietario = "Policia Municipal",
                        time = "10:00 AM",
                        perfil = R.drawable.pefil_police,
                        mesage = "Se reporta una fuga de agua en C. Falsa, col. Falsa, se recomienda tener precauciones ya que se cerran de forma temporal las calles.",
                        //imagesList: List<Int>
                        numLikes = "10"
                    )

                }
            }
        }
    }

}