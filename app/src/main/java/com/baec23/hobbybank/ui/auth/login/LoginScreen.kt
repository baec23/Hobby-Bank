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
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.app.AppViewModel

@Composable
fun LoginScreen(appViewModel: AppViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(onClick = {
                appViewModel.showSnackbar("Hello! Login Pressed!")
                appViewModel.doNavigate(CONTENT_NAV_GRAPH_ROUTE, true)
            }) {
                Text("Login!")
            }
            OutlinedButton(onClick = { appViewModel.doNavigate(NavScreen.Signup.route) }) {
                Text("Sign up!")
            }
        }
    }
}