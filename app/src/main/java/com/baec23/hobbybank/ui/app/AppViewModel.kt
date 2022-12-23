package com.baec23.hobbybank.ui.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.repository.DataStoreRepository
import com.baec23.hobbybank.repository.UserRepository
import com.baec23.hobbybank.service.NavService
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
    private val _backdropShown: MutableState<Boolean> = mutableStateOf(false)
    val backdropShown: State<Boolean> = _backdropShown

    fun onEvent(event: AppUiEvent) {
        when (event) {
            is AppUiEvent.NavbarButtonPressed -> navService.navigate(event.destinationScreen)
            AppUiEvent.ToggleBackdrop -> _backdropShown.value = !_backdropShown.value
            AppUiEvent.LogoutPressed -> snackbarService.showSnackbar("Logout Pressed!")
        }
    }
}

sealed class AppUiEvent {
    data class NavbarButtonPressed(val destinationScreen: NavScreen) : AppUiEvent()
    object ToggleBackdrop : AppUiEvent()
    object LogoutPressed : AppUiEvent()
}
