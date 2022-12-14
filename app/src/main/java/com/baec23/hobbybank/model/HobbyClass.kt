package com.baec23.hobbybank.model

data class HobbyClass(
    val id: String = "",
    val name: String = "",
    val details: String = "",
    val location: String = "",
    val creatorUserId: String = "",
    val sessions: List<HobbySession> = listOf(),
)