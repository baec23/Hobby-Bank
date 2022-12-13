package com.baec23.hobbybank.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.myaccount.MyAccountScreen
import com.baec23.hobbybank.ui.main.createclass.CreateClassScreen
import com.baec23.hobbybank.ui.main.viewclasses.ViewClassesScreen

data class BottomNavItem(
    val name: String,
    val screen: NavScreen,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        icon = Icons.Default.Home,
        screen = NavScreen.Home
    ),
    BottomNavItem(
        name = "Create Class",
        icon = Icons.Default.List,
        screen = NavScreen.CreateClass
    ),
    BottomNavItem(
        name = "My Account",
        icon = Icons.Default.Face,
        screen = NavScreen.MyAccount
    )
)

fun NavGraphBuilder.contentNavGraph() {
    navigation(
        startDestination = NavScreen.Home.route,
        route = CONTENT_NAV_GRAPH_ROUTE
    ) {
        composable(route = NavScreen.Home.route) {
            ViewClassesScreen()
        }
        composable(route = NavScreen.CreateClass.route) {
            CreateClassScreen()
        }
        composable(route = NavScreen.MyAccount.route) {
            MyAccountScreen()
        }
    }
}