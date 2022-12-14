package com.baec23.hobbybank.ui.app

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.navigation.NavService
import com.baec23.hobbybank.repository.DataStoreRepository
import com.baec23.hobbybank.repository.UserRepository
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val navService: NavService,
    val snackbarService: SnackbarService,
    val navController: NavHostController
) : ViewModel() {

    val currNavScreen = navService.currNavScreen

    fun onEvent(event: AppUiEvent) {
        when (event) {
            is AppUiEvent.NavbarButtonPressed -> navService.navigate(event.destinationScreen)
            AppUiEvent.LogoutPressed -> snackbarService.showSnackbar("Logout Pressed!")
        }
    }
}

sealed class AppUiEvent {
    data class NavbarButtonPressed(val destinationScreen: NavScreen) : AppUiEvent()
    object LogoutPressed : AppUiEvent()
}
