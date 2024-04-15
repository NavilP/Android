package com.example.proy_comunitario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proy_comunitario.ui.screens.AlertScreen
import com.example.proy_comunitario.ui.screens.CreateForo
import com.example.proy_comunitario.ui.screens.DetallesForo
import com.example.proy_comunitario.ui.screens.DirectorioScreen
import com.example.proy_comunitario.ui.screens.ForosScreen
import com.example.proy_comunitario.ui.screens.HomeScreen
import com.example.proy_comunitario.ui.screens.InicioScreen
import com.example.proy_comunitario.ui.screens.LoginScreen
import com.example.proy_comunitario.ui.screens.Notifications
import com.example.proy_comunitario.ui.screens.ProblemasScreen
import com.example.proy_comunitario.ui.screens.SignScreen
import com.example.proy_comunitario.ui.theme.PrincipalGreen
import com.example.proy_comunitario.ui.theme.Proy_ComunitarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proy_ComunitarioTheme {
                Principal()
            }
        }
    }
}

@Composable
fun Principal() {
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF61B965)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(100.dp, 100.dp)
        )
    }*/

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        // Pantalla de inicio
        composable(route = "home") {
            InicioScreen(navController = navController)
        }

        // Login
        composable(route = "login") {
            LoginScreen(navController = navController)
        }

        //Signin
        composable(route = "signin") {
            SignScreen(navController = navController)
        }

        // Foros
        composable(route = "foros") {
            HomeScreen(navController = navController)
        }

        // Alertas
        composable(route = "alertas") {
            AlertScreen(navController = navController)
        }

        // Directorio
        composable(route = "directorio") {
            DirectorioScreen(navController = navController)
        }

        // Problemas
        composable(route = "problemas") {
            ProblemasScreen(navController = navController)
        }

        // Detalles de foro
        composable(route = "detalles/{propietario}/{perfil}/{message}/{title}", arguments = listOf(
            navArgument("propietario") { type = NavType.StringType },
            navArgument("perfil") { type = NavType.IntType },
            navArgument("message") { type = NavType.StringType },
            navArgument("title") { type = NavType.StringType }
        )) {
                navBackStackEntry ->
            DetallesForo(
                propietario = navBackStackEntry.arguments?.getString("propietario") ?: "",
                perfil = navBackStackEntry.arguments?.getInt("perfil") ?: 0,
                message = navBackStackEntry.arguments?.getString("message") ?: "",
                title = navBackStackEntry.arguments?.getString("title") ?: "",
                navController = navController
            )
        }

        // Añadir foro
        composable(route = "createforo") {
            CreateForo(navController = navController)
        }

        // Notificaciones
        composable(route = "notificaciones") {
            Notifications(navController = navController)
        }

    }


}

@Preview(showBackground = true)
@Composable
fun PrincipalPreview() {
    Proy_ComunitarioTheme {
        Principal()
    }
}