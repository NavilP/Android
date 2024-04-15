package com.example.proy_comunitario.ui.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.MediaSession2Service
import android.os.Build
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proy_comunitario.R
import com.example.proy_comunitario.ui.screens.AlertScreen
import com.example.proy_comunitario.ui.screens.DetallesForo
import com.example.proy_comunitario.ui.screens.DirectorioScreen
import com.example.proy_comunitario.ui.screens.HomeScreen
import com.example.proy_comunitario.ui.screens.ProblemasScreen
import com.example.proy_comunitario.ui.theme.PrincipalGreen


// Título de la App y perfil de usuario
@Composable
fun AppTitle(
    photoResId: Int,
    title: String,
    iconResId: Int,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = photoResId),
            contentDescription = "Perfil",
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
        )
        Text(
            text = title,
            color = PrincipalGreen,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .weight(0.5f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Icon(
            Icons.Rounded.Notifications,
            contentDescription = stringResource(id = iconResId),
            tint = PrincipalGreen,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate("notificaciones") }
        )
    }
}

// Card para pantalla principal de foros
@Composable
fun ForoCard(
    photoResId: Int,
    name: String,
    message: String,
    title: String,
    navController: NavController
) {
    Column {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .clickable {
                    navController.navigate("detalles/$name/$photoResId/$message/$title")
                }
                /*.shadow(
                    elevation = 10.dp,
                    spotColor = PrincipalGreen,
                    shape = RoundedCornerShape(10.dp)
                )*/
                .drawBehind {
                    val shadowColor = PrincipalGreen.copy(alpha = 0.6f)
                    val shadowLeftRadius = 3.dp.toPx()
                    val shadowBottomRadius = 3.dp.toPx()

                    // Sombra izquierda
                    drawRoundRect(
                        color = shadowColor,
                        topLeft = Offset(0f, size.height - shadowBottomRadius),
                        cornerRadius = CornerRadius(shadowLeftRadius, 0f)
                    )

                    // Sombra inferior
                    drawRoundRect(
                        color = shadowColor,
                        topLeft = Offset(shadowLeftRadius, size.height - shadowBottomRadius),
                        size = Size(size.width - shadowLeftRadius, shadowBottomRadius),
                        cornerRadius = CornerRadius(0f, shadowBottomRadius)
                    )
                }
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Image(
                    painter = painterResource(id = photoResId),
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = name,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    )
                    Text(
                        text = message,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent))
    }
}

// Funciones en prueba
@Composable
fun NavegacionInf(destination: MutableState<String>, navController: NavController) {
    // Barra de navegación
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("foros", "alertas", "directorio", "problemas").forEach { seccion ->
                Icon(
                    painter = painterResource(id = obtenerIcono(seccion, destination)),
                    contentDescription = seccion,
                    tint = if (destination.value == seccion) Color.White else PrincipalGreen,
                    modifier = Modifier
                        .size(if (destination.value == seccion) 45.dp else 35.dp)
                        .clickable {
                            destination.value = seccion
                            navController.navigate(seccion)
                        }
                        .background(if (destination.value == seccion) PrincipalGreen else Color.Transparent)
                        .padding(if (destination.value == seccion) 10.dp else 0.dp)
                )
            }
        }
    }
}


// Función para obtener el icono correspondiente a una sección
@Composable
fun obtenerIcono(seccion: String, destination: MutableState<String>): Int {
    return when (seccion) {
        "foros" -> if (destination.value == "foros") R.drawable.foro_icon_active else R.drawable.foro_icon
        "alertas" -> if (destination.value == "alertas") R.drawable.alert_icon_active else R.drawable.alert_icon
        "directorio" -> if (destination.value == "directorio") R.drawable.directorio_icon_active else R.drawable.directorio_icon
        "problemas" -> if (destination.value == "problemas") R.drawable.problema_icon_active else R.drawable.problema_icon
        else -> R.drawable.ic_launcher_foreground
    }
}

@Composable
fun AlertCard(
    propietario: String,
    time: String,
    perfil: Int,
    mesage: String,
    numLikes: String
    //imagesList: List<Int>
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(360.dp)
            .padding(15.dp)
    ) {
        Column {
            // Perfil del usuario
            Row {
                Image(
                    painter = painterResource(id = perfil),
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = propietario,
                        fontSize = 18.sp
                    )
                    Text(
                        text = time,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mensaje de alerta
            Text(text = mesage, fontSize = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)

            Spacer(modifier = Modifier.height(10.dp))

            // Imagenes de alerta
            val imageSliderList = listOf(
                R.drawable.alerta1,
                R.drawable.alerta2
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.alerta1),
                    contentDescription = "Alerta",
                    modifier = Modifier
                        .size(180.dp, 180.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp).background(Color.Transparent))

            // Barra larga
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
                .padding(vertical = 10.dp))

            Spacer(modifier = Modifier.height(8.dp).background(Color.Transparent))

            // Icono de likes y texto de número de likes
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.like_icon),
                        contentDescription = "Likes",
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = numLikes,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
            }

        }
    }

    Spacer(modifier = Modifier.height(30.dp))
}


@Composable
fun imageSlider(
    imageList: List<Int>
) {

}

@Composable
fun ComentCard(
    perfil: Int,
    propietario: String,
    message: String,
    numLikes: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = perfil),
            contentDescription = "Perfil",
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            Text(text = propietario, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
            Text(text = message, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .background(Color.Transparent)
            )

            // Barra larga
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
                    .padding(vertical = 10.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .background(Color.Transparent)
            )

            // Icono de likes y texto de número de likes
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.like_icon),
                        contentDescription = "Likes",
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = numLikes,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
            }
        }
    }

    Spacer(
        modifier = Modifier
            .height(15.dp)
            .background(Color.Transparent)
    )
}

@Composable
fun CheckboxApp() {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "Toda información que comparto en este foro es verdadera y precisa según mi conocimiento",
            modifier = Modifier.clickable { isChecked.value = !isChecked.value }
        )
    }
}

@Composable
fun NotiCard(
    photoResId: Int,
    name: String,
    type: String
) {
    Row(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(15.dp)
    ) {
        Image(
            painter = painterResource(id = photoResId),
            contentDescription = "Perfil",
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
            Text(
                text = if(type == "Alerta") "Agregó una alerta" else "Agregó un nuevo foro",
                fontSize = 18.sp
            )
        }
    }
}