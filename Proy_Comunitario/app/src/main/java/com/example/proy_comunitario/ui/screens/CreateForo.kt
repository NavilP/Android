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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.AppTitle
import com.example.proy_comunitario.ui.presentation.CheckboxApp
import com.example.proy_comunitario.ui.presentation.NavegacionInf
import com.example.proy_comunitario.ui.theme.HomeBackground
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun CreateForo( navController: NavController) {
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
                            text = "Iniciar foro",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = Color(0xFF1E1E1E),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(end = 40.dp)
                        )
                    }

                    // Formulario
                    Column (
                        modifier = Modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row (modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.Start) {
                            Text(text = "Comarte tus ideas.", fontSize = 20.sp)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        var text by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            modifier = Modifier
                                .size(315.dp, 50.dp),
                            value = text,
                            onValueChange = { newText ->
                                // Guardar el campo de contraseña
                                text = newText
                            },
                            placeholder = { Text("Nombre del foro", color = Color.DarkGray, fontSize = 18.sp) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                        
                        Spacer(modifier = Modifier.height(20.dp))

                        var text2 by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            modifier = Modifier
                                .size(315.dp, 208.dp),
                            value = text2,
                            onValueChange = { newText2 ->
                                // Guardar el campo de contraseña
                                text2 = newText2
                            },
                            placeholder = { Text("Descripcion", color = Color.DarkGray, fontSize = 18.sp) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )

                        CheckboxApp()

                        val mainButtonColor = ButtonDefaults.buttonColors(
                            containerColor = PrincipalGreen
                        )
                        Button(
                            modifier = Modifier
                                .size(282.dp, 53.dp),
                            shape = RoundedCornerShape(40.dp),
                            onClick = { navController.navigate("foros") },
                            colors = mainButtonColor
                        ) {
                            Text(
                                text = "Registrar",
                                color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }


                }
            }


        }
    }
}