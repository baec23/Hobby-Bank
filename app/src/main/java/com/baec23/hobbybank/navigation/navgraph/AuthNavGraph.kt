package com.baec23.hobbybank.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.AUTH_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.auth.login.LoginScreen
import com.baec23.hobbybank.ui.auth.signup.SignupScreen
import com.baec23.hobbybank.ui.main.viewclassdetails.ViewClassDetailsScreen

fun NavGraphBuilder.authNavGraph() {
    navigation(
        startDestination = NavScreen.Login.route,
        route = AUTH_NAV_GRAPH_ROUTE
    ) {
        composable(route = NavScreen.Login.route) {
//            LoginScreen()
            ViewClassDetailsScreen()
        }
        composable(route = NavScreen.Signup.route) {
            SignupScreen()
        }
    }
}