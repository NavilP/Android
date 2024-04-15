package com.example.proy_comunitario.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun SignScreen(navController: NavController, viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
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
            Button(onClick = { navController.navigate("home") }, colors = btnT) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.arrow_back_content_desc),
                    tint = PrincipalGreen,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 20.dp, bottom = 0.dp)
                )
            }
        }

        Text(
            text = "Registrarse",
            color = PrincipalGreen,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(40.dp))

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

                var nombre = rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        //.background(Color.White)
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = nombre.value,
                    onValueChange = { newNombre ->
                        // Guardar el campo de correo
                        nombre.value = newNombre
                    },
                    placeholder = { Text("Nombre", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                //var text2 by remember { mutableStateOf(TextFieldValue("")) }
                var apellido = rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = apellido.value,
                    onValueChange = { newApellido ->
                        // Guardar el campo de contraseña
                        apellido.value = newApellido
                    },
                    placeholder = { Text("Apellido", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                //var text3 by remember { mutableStateOf(TextFieldValue("")) }
                var email = rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = email.value,
                    onValueChange = { newEmail ->
                        // Guardar el campo de contraseña
                        email.value = newEmail
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    placeholder = { Text("Correo electrónico", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                //var text4 by remember { mutableStateOf(TextFieldValue("")) }
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
                        unfocusedTextColor = Color.White
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

                Spacer(modifier = Modifier.height(10.dp))

                //var text5 by remember { mutableStateOf(TextFieldValue("")) }
                var password2 = rememberSaveable { mutableStateOf("") }
                val passwordVisible2 = rememberSaveable {
                    mutableStateOf(false)
                }

                val visualTransformation2 = if (passwordVisible2.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()

                TextField(
                    modifier = Modifier
                        .size(282.dp, 75.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    value = password2.value,
                    onValueChange = { newPassword2 ->
                        // Guardar el campo de contraseña
                        password2.value = newPassword2
                    },
                    keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
                    placeholder = { Text("Confirmar contraseña", color = Color.DarkGray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    visualTransformation = visualTransformation2,
                    trailingIcon = {
                        if (password2.value.isNotBlank()){
                            val image =
                                if (passwordVisible2.value)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility

                            IconButton(onClick = { passwordVisible2.value = !passwordVisible2.value }) {
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

                val mainButtonColor = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E1E1E)
                )
                Button(
                    modifier = Modifier
                        .size(282.dp, 53.dp),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {
                        viewModel.createUserWithEmailAndPassword(email.value, password.value){
                            navController.navigate("foros")
                        }
                    },
                    colors = mainButtonColor
                ) {
                    Text(
                        text = "Registrar",
                        color = Color(0xFF61B965),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = "¿Ya tienes una cuenta?",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Inicia sesión",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}