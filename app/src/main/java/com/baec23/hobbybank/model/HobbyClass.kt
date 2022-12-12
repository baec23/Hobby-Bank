package com.baec23.hobbybank.model

data class HobbyClass(
    val name: String = "",
    val details: String = "",
    val location: String = "",
    val sessions: List<HobbySession> = listOf(),
)