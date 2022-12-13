package com.baec23.hobbybank.ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.ContentNavScreen
import com.baec23.hobbybank.ui.app.comp.BottomNavItem
import com.baec23.hobbybank.ui.app.comp.BottomNavigationBar
import com.baec23.hobbybank.ui.app.comp.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    var currScreenName = remember { "Hobby Bank" }
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.height(60.dp),
                screenName = currScreenName,
                onLogout = {})
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .height(50.dp),
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        icon = Icons.Default.Home,
                        screen = ContentNavScreen.Home
                    ),
                    BottomNavItem(
                        name = "Create Class",
                        icon = Icons.Default.List,
                        screen = ContentNavScreen.CreateClass
                    ),
                ), onItemClick = {
                    currScreenName = it.screen.toString()
                    navController.navigate(it.screen.route)
                })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}