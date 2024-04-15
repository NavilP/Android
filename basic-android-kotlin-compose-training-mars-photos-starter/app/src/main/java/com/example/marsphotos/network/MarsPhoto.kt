package com.example.marsphotos.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@SerialName(value = "img_src")

@Serializable
data class MarsPhoto(
    val id: String,  val img_src: String
)