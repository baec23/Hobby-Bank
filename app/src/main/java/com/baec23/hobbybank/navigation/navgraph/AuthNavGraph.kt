package com.baec23.hobbybank.navigation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.baec23.hobbybank.navigation.AUTH_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.auth.signup.SignupScreen
import com.baec23.hobbybank.ui.main.tosscopy.BenefitsScreen

fun NavGraphBuilder.authNavGraph() {
    navigation(
        startDestination = NavScreen.Login.route,
        route = AUTH_NAV_GRAPH_ROUTE
    ) {
        composable(route = NavScreen.Login.route) {
//            LoginScreen()
//            ViewClassDetailsScreen()
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF18171d)) {
                BenefitsScreen()
            }
        }
        composable(route = NavScreen.Signup.route) {
            SignupScreen()
        }
    }
}