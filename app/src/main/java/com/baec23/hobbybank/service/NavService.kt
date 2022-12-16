package com.baec23.hobbybank.service

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.ui.app.TAG
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Singleton
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

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