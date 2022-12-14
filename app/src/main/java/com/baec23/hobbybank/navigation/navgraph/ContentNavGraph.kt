package com.baec23.hobbybank.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.main.createclass.CreateClassScreen
import com.baec23.hobbybank.ui.main.myaccount.MyAccountScreen
import com.baec23.hobbybank.ui.main.myclassschedule.MyClassScheduleScreen
import com.baec23.hobbybank.ui.main.viewclasses.ViewClassesScreen

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
        composable(route = NavScreen.MyClassSchedule.route) {
            MyClassScheduleScreen()
        }
    }
}

data class BottomNavItem(
    val name: String,
    val screen: NavScreen,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "메인",
        icon = Icons.Default.Home,
        screen = NavScreen.Home
    ),
    BottomNavItem(
        name = "일정표",
        icon = Icons.Default.DateRange,
        screen = NavScreen.MyClassSchedule
    ),
    BottomNavItem(
        name = "내 계정",
        icon = Icons.Default.Face,
        screen = NavScreen.MyAccount
    )
)

