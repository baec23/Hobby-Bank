package com.baec23.hobbybank.ui.auth.signup

data class SignupFormState(
    val username: String = "",
    val password1: String = "",
    val password2: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
)
