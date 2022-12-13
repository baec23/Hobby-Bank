package com.baec23.hobbybank.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.AUTH_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.AuthNavScreen
import com.baec23.hobbybank.ui.auth.login.LoginScreen
import com.baec23.hobbybank.ui.auth.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AuthNavScreen.Login.route,
        route = AUTH_NAV_GRAPH_ROUTE
    ) {
        composable(route = AuthNavScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthNavScreen.Signup.route) {
            SignupScreen(navController = navController)
        }
    }
}