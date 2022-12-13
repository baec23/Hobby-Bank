package com.baec23.hobbybank.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.ContentNavScreen
import com.baec23.hobbybank.ui.app.App
import com.baec23.hobbybank.ui.main.createclass.CreateClassScreen
import com.baec23.hobbybank.ui.main.viewclasses.ViewClassesScreen

fun NavGraphBuilder.contentNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = ContentNavScreen.Home.route,
        route = CONTENT_NAV_GRAPH_ROUTE
    ) {
        composable(route = ContentNavScreen.Home.route) {
            App(navController = navController) {
                ViewClassesScreen()
            }
        }
        composable(route = ContentNavScreen.CreateClass.route) {
            App(navController = navController) {
                CreateClassScreen()
            }
        }
    }
}