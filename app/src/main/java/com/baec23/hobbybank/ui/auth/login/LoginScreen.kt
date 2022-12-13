package com.baec23.hobbybank.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.AuthNavScreen
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(onClick = {
                navController.popBackStack()
                navController.navigate(CONTENT_NAV_GRAPH_ROUTE) {
                }
            }) {
                Text("Login!")
            }
            OutlinedButton(onClick = { navController.navigate(AuthNavScreen.Signup.route) }) {
                Text("Sign up!")
            }
        }
    }
}