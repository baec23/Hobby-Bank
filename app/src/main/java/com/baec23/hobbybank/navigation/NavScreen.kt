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
        displayName = "Create Class",
        parentRoute = "my_account"
    )

    object MyAccount : NavScreen(
        route = "my_account_screen",
        displayName = "My Account"
    )

    object MyClassSchedule : NavScreen(
        route = "my_class_schedule_screen",
        displayName = "일정표"
    )

    object ViewClassDetails : NavScreen(
        route = "view_class_details_screen",
        displayName = "수업 상세",
        parentRoute = "home_screen"
    )

    object EditMyProfile : NavScreen(
        route = "edit_my_profile_screen",
        displayName = "나의 프로필",
        parentRoute = "my_account_screen"
    )
}