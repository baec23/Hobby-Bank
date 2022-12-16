package com.baec23.hobbybank.navigation

sealed class NavGraph(
    route: String,
    val startDestination: String
) : NavDestination(route) {

    object Auth : NavGraph(
        route = "auth_graph",
        startDestination = NavScreen.Login.route
    )

    object Main : NavGraph(
        route = "main_graph",
        startDestination = NavScreen.Home.route
    )

    object CreateClass : NavGraph(
        route = "create_class_graph",
        startDestination = NavScreen.CreateClass.route
    )
}
