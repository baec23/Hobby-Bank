package com.baec23.hobbybank.service

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.NavScreen
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class NavService constructor(
    private val navController: NavHostController
) {
    private val routeNavScreenMap: MutableMap<String, NavScreen> = mutableMapOf()
    private val _currNavScreen: MutableState<NavScreen> = mutableStateOf(NavScreen.Login)
    val currNavScreen: State<NavScreen> = _currNavScreen

    fun navigate(to: NavScreen, clearBackStack: Boolean = false) {
        if (clearBackStack) navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to.route)
    }

    fun navigate(to: String, clearBackStack: Boolean = false) {
        if (clearBackStack) navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to)
    }

    init {
        NavScreen::class.sealedSubclasses.forEach { screenKClass ->
            val navScreen = screenKClass.objectInstance ?: return@forEach
            val route = navScreen::route.get()
            routeNavScreenMap[route] = navScreen
        }

        MainScope().launch {
            navController.currentBackStackEntryFlow.collect {
                _currNavScreen.value = routeNavScreenMap[it.destination.route] ?: return@collect
            }
        }
    }
}