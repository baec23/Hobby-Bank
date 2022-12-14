package com.baec23.hobbybank.model

import com.google.firebase.firestore.Exclude

data class User(
    @get:Exclude val id: String = "",
    val username: String = "",
    val password: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
    val location: String = ""
)
