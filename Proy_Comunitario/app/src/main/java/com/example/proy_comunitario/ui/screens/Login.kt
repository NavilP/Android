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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proy_comunitario.Principal
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.presentation.LoginScreenViewModel
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            val btnT = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )

            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back_content_desc),
                tint = PrincipalGreen,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 20.dp, bottom = 0.dp)
                    .clickable { navController.navigate("home") }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "logo2",
            modifier = Modifier
                .size(150.dp, 150.dp)
                .padding(top = 0.dp, bottom = 20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PrincipalGreen, shape = RoundedCornerShape(
                        topStart = 80.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenid@",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Conexión Comunitaria: Participa, colabora y haz la diferencia. ¡Conéctate ahora!",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))

                var email = rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        //.background(Color.White)
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = email.value,
                    onValueChange = { newEmail ->
                        // Guardar el campo de correo
                        email.value = newEmail
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    placeholder = { Text("Correo Electronico", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                var password = rememberSaveable { mutableStateOf("") }
                val passwordVisible = rememberSaveable {
                    mutableStateOf(false)
                }

                val visualTransformation = if (passwordVisible.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()

                TextField(
                    modifier = Modifier
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = password.value,
                    onValueChange = { newPassword ->
                        // Guardar el campo de contraseña
                        password.value = newPassword
                    },
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
                    placeholder = { Text("Contraseña", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    visualTransformation = visualTransformation,
                    trailingIcon = {
                        if (password.value.isNotBlank()){
                            val image =
                                if (passwordVisible.value)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility

                            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = "Visibilidad"
                                )
                            }
                        }
                        else null
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                val valido = remember(email.value, password.value){
                    email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
                }

                val mainButtonColor = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E1E1E)
                )
                Button(
                    modifier = Modifier
                        .size(282.dp, 53.dp),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {
                        viewModel.signInWithEmailAndPassword(email.value, password.value){
                            navController.navigate("foros")
                        } },
                    colors = mainButtonColor,
                    enabled = valido
                ) {
                    Text(
                        text = "Iniciar Sesion",
                        color = Color(0xFF61B965),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Iniciar sesión con", color = Color.Black, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = "¿No tienes una cuenta?",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Registrate",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}