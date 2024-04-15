package com.example.proy_comunitario.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.theme.PrincipalGreen

@Composable
fun InicioScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "logo2",
            modifier = Modifier
                .size(200.dp, 236.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))

        // Recuadro verde
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = PrincipalGreen, shape = RoundedCornerShape(
                        topStart = 80.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Conexión",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Comunitaria",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(50.dp))
            val mainButtonColor = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
            Button(
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(282.dp, 75.dp),
                onClick = {
                    navController.navigate("login")
                },
                colors = mainButtonColor
            )
            {
                Text(
                    text = "Iniciar Sesión",
                    color = PrincipalGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(282.dp, 75.dp),
                onClick = { navController.navigate("signin") },
                colors = mainButtonColor
            )
            {
                Text(
                    text = "Registrarse",
                    color = PrincipalGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}