package com.baec23.hobbybank.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baec23.hobbybank.navigation.auth.authNavGraph
import com.baec23.hobbybank.navigation.navgraph.contentNavGraph

const val AUTH_NAV_GRAPH_ROUTE = "auth"
const val ROOT_NAV_GRAPH_ROUTE = "root"

@Composable
fun RootNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_NAV_GRAPH_ROUTE,
        route = ROOT_NAV_GRAPH_ROUTE
    ) {
        authNavGraph(navController = navController)
        contentNavGraph(navController = navController)
    }
}