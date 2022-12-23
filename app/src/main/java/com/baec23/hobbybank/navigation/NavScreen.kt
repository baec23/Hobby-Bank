package com.baec23.hobbybank.navigation

sealed class NavScreen(
    val route: String,
    val displayName: String = "",
    val shouldShowNavBars: Boolean = true,
    val parentRoute: String? = null,
){
    //Auth
    object Login : NavScreen(
        route = "login_screen",
        displayName = "로그인",
        shouldShowNavBars = false
    )

    object Signup : NavScreen(
        route = "signup_screen",
        displayName = "회원 가입",
        shouldShowNavBars = false
    )

    //Main
    object Home : NavScreen(
        route = "home_screen",
        displayName = "취미 은행"
    )

    object CreateClass : NavScreen(
        route = "create_class_screen",
        displayName = "새로운 수업 만들기",
        parentRoute = "my_account"
    )

    object MyAccount : NavScreen(
        route = "my_account_screen",
        displayName = "내 계정"
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
        displayName = "내 프로필",
        parentRoute = "my_account_screen"
    )
}