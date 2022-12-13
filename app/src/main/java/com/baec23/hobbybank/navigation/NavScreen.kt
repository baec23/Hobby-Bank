package com.baec23.hobbybank.navigation

sealed class NavScreen(
    val route: String,
    val displayName: String = "",
    val shouldShowNavBars: Boolean = true,
    val parentRoute: String? = null,
) {
    //Auth
    object Login : NavScreen(
        route = "login_screen",
        displayName = "Login",
        shouldShowNavBars = false
    )

    object Signup : NavScreen(
        route = "signup_screen",
        displayName = "Sign Up",
        shouldShowNavBars = false
    )

    //Main
    object Home : NavScreen(
        route = "home_screen",
        displayName = "Home"
    )

    object CreateClass : NavScreen(
        route = "create_class_screen",
        displayName = "Create Class"
    )

    object MyAccount : NavScreen(
        route = "my_account",
        displayName = "My Account"
    )
}