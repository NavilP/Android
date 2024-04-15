package com.example.gesto5_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gesto5_2.ui.theme.Gesto5_2Theme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gesto5_2Theme {
                var scale by remember {
                    mutableStateOf(1f)
                }
                var offset by remember {
                    mutableStateOf(Offset.Zero)
                }
                var rotationAngle by remember {
                    mutableStateOf(0f)
                }

                var showOptions by remember {
                    mutableStateOf(false)
                }

                var menuAnchor by remember { mutableStateOf<Offset?>(null) }

                var dragOffset by remember { mutableStateOf(Offset.Zero) }

                val backgroundColor = Color(0xFFFFFFFF)
                val alpha = 0.7f

                val image: Painter = painterResource(id = R.drawable.wall)


                BoxWithConstraints(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, rotation ->
                                scale *= zoom
                                offset += pan
                                rotationAngle += rotation
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val state = rememberTransformableState{ zoomChange, panChange, rotationChange ->
                        scale = (scale * zoomChange).coerceIn(1f, 5f)

                        val extraWidth = (scale - 1) * constraints.maxWidth
                        val extraHeight = (scale - 1) * constraints.maxHeight

                        val maxX = extraWidth / 2
                        val maxY = extraHeight / 2

                        offset = Offset(
                            x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                            y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
                        )
                    }

                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        showOptions = true

                                    }
                                )
                            }
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                                rotationZ = rotationAngle
                            }
                            .transformable(state)

                    )

                    Text(
                        text = "Long Press here",
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(4.dp),
                        textAlign = TextAlign.Center
                    )


                    if (showOptions) {
                        val x = menuAnchor?.x?.dp ?: 0.dp
                        val y = menuAnchor?.y?.dp ?: 0.dp

                        BoxWithConstraints(modifier = Modifier
                            .background(color = Color.Transparent)
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    dragOffset += dragAmount
                                }
                            }
                            .offset { IntOffset(dragOffset.x.roundToInt(), dragOffset.y.roundToInt()) },
                            contentAlignment = Alignment.Center

                        ) {

                            Column(
                                modifier = Modifier
                                    .background(
                                        backgroundColor.copy(alpha = alpha),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .width(300.dp)
                                    .height(200.dp)
                            ) {
                                Text(
                                    text = "Saludar al pingüino",
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center,
                                )
                                Divider(color = Color.White)
                                Text(
                                    text = "Mover al pingüino",
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                                Divider(color = Color.White)
                                Text(
                                    text = "Cerrar",
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                                Divider(color = Color.White)
                            }
                        }


                    }

                }
            }
        }
    }
}