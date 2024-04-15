package com.example.proy_comunitario.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.ForoCard
import com.example.proy_comunitario.ui.theme.HomeBackground
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun ForosScreen(navController: NavController){

    val activity = LocalContext.current as? ComponentActivity

    BackHandler(enabled = true) {
        activity?.finishAffinity()
    }

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Foros", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
                Icon(
                    Icons.Rounded.AddCircle,
                    contentDescription = stringResource(id = R.string.add_circle_content_desc),
                    tint = PrincipalGreen,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { navController.navigate("createforo") }
                )

            }

            // Foros
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                // Foros de hoy
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Hoy", fontSize = 20.sp, modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                    ) {
                        ForoCard(
                            photoResId = R.drawable.perfil3,
                            name = "Juan Perez",
                            message = "¿Que les parece organizar una limpieza de nuestras calles y el parque de la colonia? Recientemente los problemas de basura y por ende el mal olor han causado incomodidades en los residentes.",
                            title = "Limpieza de calle",
                            navController = navController
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                // Foros de ayer
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Ayer", fontSize = 20.sp, modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ForoCard(
                            photoResId = R.drawable.perfil1,
                            name = "Ana Perez",
                            message = "Que les parece organizar una reunion hoy alrededor de las 5PM",
                            title = "Reunion de vecinos",
                            navController = navController
                        )

                        ForoCard(
                            photoResId = R.drawable.perfil2,
                            name = "Sofía Perez",
                            message = "Que les parece organizar una reunion hoy alrededor de las 5PM",
                            title = "Reunion de vecinos",
                            navController = navController
                        )

                        ForoCard(
                            photoResId = R.drawable.perfil4,
                            name = "Luis Perez",
                            message = "Que les parece organizar una reunion hoy alrededor de las 5PM",
                            title = "Reunion de vecinos",
                            navController = navController
                        )
                    }
                }

            }

        }
    }

}