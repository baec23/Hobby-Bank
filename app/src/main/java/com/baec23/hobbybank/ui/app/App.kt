package com.baec23.hobbybank.ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.navigation.RootNavHost
import com.baec23.hobbybank.navigation.navgraph.bottomNavItems
import com.baec23.hobbybank.ui.app.comp.BottomNavigationBar
import com.baec23.hobbybank.ui.app.comp.TopBar

const val TAG: String = "App"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val currScreen by appViewModel.currNavScreen

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = appViewModel.snackbarService.snackbarState) },
        topBar = {
            if (currScreen.shouldShowNavBars) {
                TopBar(
                    modifier = Modifier.height(60.dp),
                    screenName = currScreen.displayName,
                    onLogout = { appViewModel.onEvent(AppUiEvent.LogoutPressed) })
            }
        },
        bottomBar = {
            if (currScreen.shouldShowNavBars) {
                BottomNavigationBar(
                    modifier = Modifier
                        .height(60.dp),
                    items = bottomNavItems,
                    currScreen = currScreen
                ) {
                    appViewModel.onEvent(AppUiEvent.NavbarButtonPressed(it.screen))
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            RootNavHost(navController = appViewModel.navController)
        }
    }
}