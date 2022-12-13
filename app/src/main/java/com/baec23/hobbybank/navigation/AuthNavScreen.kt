package com.baec23.hobbybank.navigation

sealed class AuthNavScreen(val route: String) {
    object Login : AuthNavScreen("login_screen")
    object Signup : AuthNavScreen("signup_screen")
}
