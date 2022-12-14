package com.baec23.hobbybank.navigation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.baec23.hobbybank.ui.app.TAG
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@ActivityScoped
class NavService constructor(
    private val navController: NavHostController
) {
    private val _currNavScreen: MutableState<NavScreen> = mutableStateOf(NavScreen.Home)
    val currNavScreen: State<NavScreen> = _currNavScreen

    fun navigate(to: NavScreen, clearBackStack: Boolean = false) {
        if (clearBackStack)
            navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to.route)
    }

    fun navigate(to: String, clearBackStack: Boolean = false) {
        if (clearBackStack)
            navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to)
    }

    init {
        MainScope().launch {
            navController.currentBackStackEntryFlow.collect {
                Log.d(TAG, "currentBackstackEntryFlow: changed")
                when (it.destination.route) {
                    "login_screen" -> _currNavScreen.value = NavScreen.Login
                    "signup_screen" -> _currNavScreen.value = NavScreen.Signup
                    "home_screen" -> _currNavScreen.value = NavScreen.Home
                    "create_class_screen" -> _currNavScreen.value = NavScreen.CreateClass
                    "my_account" -> _currNavScreen.value = NavScreen.MyAccount
                }
            }
        }
    }
}