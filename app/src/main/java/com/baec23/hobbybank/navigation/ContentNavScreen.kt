package com.baec23.hobbybank.navigation

const val CONTENT_NAV_GRAPH_ROUTE = "content"

sealed class ContentNavScreen(val route: String) {
    object Home : ContentNavScreen("home_screen")
    object ViewClasses : ContentNavScreen("view_classes_screen")
    object CreateClass : ContentNavScreen("create_class_screen")
}
