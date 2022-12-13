package com.baec23.hobbybank.ui.app

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.repository.DataStoreRepository
import com.baec23.hobbybank.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
    val navController: NavHostController
) : ViewModel() {

    private val _currNavScreen: MutableState<NavScreen> = mutableStateOf(NavScreen.Home)
    val currNavScreen: State<NavScreen> = _currNavScreen
    val snackbarState = SnackbarHostState()
    fun doNavigate(to: NavScreen, clearBackStack: Boolean = false) {
        if (clearBackStack)
            navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to.route)
    }

    fun doNavigate(to: String, clearBackStack: Boolean = false) {
        if (clearBackStack)
            navController.popBackStack(currNavScreen.value.route, true)
        navController.navigate(to)
    }

    fun showSnackbar(message: String) {
        Log.d(TAG, "showSnackbar: Called with $message")
        viewModelScope.launch { snackbarState.showSnackbar(message) }
    }

    init {
        viewModelScope.launch {
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