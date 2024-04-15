package com.example.proy_comunitario.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.AppTitle
import com.example.proy_comunitario.ui.presentation.ComentCard
import com.example.proy_comunitario.ui.presentation.NavegacionInf
import com.example.proy_comunitario.ui.theme.HomeBackground
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun DetallesForo(
    propietario : String,
    perfil : Int,
    message : String,
    title: String,
    navController: NavController
) {
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
            val scrollState = rememberScrollState()

            // Boton de comentario
            val isButtonClicked = remember { mutableStateOf(false) }


            Surface (modifier = Modifier.fillMaxSize()) {
                Column (modifier = Modifier
                    .background(HomeBackground)
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                ) {
                    // Barra para regresar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.arrow_back_content_desc),
                            tint = Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 0.dp, bottom = 0.dp)
                                .clickable { navController.navigate("foros") }
                        )

                        Text(
                            text = title,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = Color(0xFF1E1E1E),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(end = 40.dp)
                        )
                    }

                    // Mensaje principal del foro
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp)
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = perfil),
                                contentDescription = "Perfil",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Text(text = propietario, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                                Text(
                                    text = message,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }

                        Spacer(modifier = Modifier
                            .height(8.dp)
                            .background(Color.Transparent))

                        // Barra larga
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                            .padding(vertical = 10.dp))

                        Spacer(modifier = Modifier
                            .height(8.dp)
                            .background(Color.Transparent))

                        // Icono de likes y texto de número de likes
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.like_icon),
                                contentDescription = "Likes",
                                tint = Color(0xFF1976D2),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "21",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    // Boton para dar like y agregar comentario
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = { /*TODO*/}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                            Icon(
                                painter = painterResource(id = R.drawable.like_icon),
                                contentDescription = "Likes",
                                tint = Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Like",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        Button(
                            onClick = {
                                isButtonClicked.value = !isButtonClicked.value },
                            colors = ButtonDefaults.buttonColors(containerColor = if (isButtonClicked.value) PrincipalGreen else Color.White)) {
                            Icon(
                                painter = painterResource(id = R.drawable.comentario_icon),
                                contentDescription = "Comentario",
                                tint = if (isButtonClicked.value) Color.White else Color.Black,
                                modifier = Modifier.size(25.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Comentario",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isButtonClicked.value) Color.White else Color.Black
                            )
                        }
                    }

                    if (isButtonClicked.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White, shape = RoundedCornerShape(
                                        topStart = 50.dp,
                                        topEnd = 0.dp,
                                        bottomStart = 50.dp,
                                        bottomEnd = 50.dp
                                    )
                                )
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.emoji_icon),
                                contentDescription = "Emoji",
                                tint = Color(0xFF979797),
                                modifier = Modifier.size(25.dp)
                            )

                            var comentario = rememberSaveable { mutableStateOf("") }

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = comentario.value,
                                onValueChange = { newComentario ->
                                    // Guardar el comentario
                                    comentario.value = newComentario
                                },
                                placeholder = { Text("Escribir comentario", color = Color.DarkGray) },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.send_icon),
                                contentDescription = "Enviar",
                                tint = Color(0xFF979797),
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }

                    // Seccion de comentarios
                    Column (modifier = Modifier.padding(15.dp)) {
                        // Titulo
                        Text(
                            text = "Comentarios",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        // Comentarios
                        ComentCard(
                            perfil = R.drawable.perfil1,
                            propietario = "Ana Perez",
                            message = "Me parece una excelente idea, propongo una reunión el día Sabado a las 15hrs.",
                            numLikes = "21"
                        )

                        ComentCard(
                            perfil = R.drawable.perfil4,
                            propietario = "Luis Perez",
                            message = "Me parece una excelente idea, propongo una reunión el día Sabado a las 15hrs.",
                            numLikes = "21"
                        )

                        ComentCard(
                            perfil = R.drawable.perfil2,
                            propietario = "Sofía Perez",
                            message = "Me parece una excelente idea, propongo una reunión el día Sabado a las 15hrs.",
                            numLikes = "21"
                        )
                    }
                }
            }
        }
    }
}