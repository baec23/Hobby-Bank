package com.baec23.hobbybank.model

import android.graphics.Bitmap

data class HobbyClass(
    val id: String = "",
    val name: String = "",
    val details: String = "",
    val location: String = "",
    val creatorUserId: String = "",
    val bitmapUrls: List<String> = listOf()
)