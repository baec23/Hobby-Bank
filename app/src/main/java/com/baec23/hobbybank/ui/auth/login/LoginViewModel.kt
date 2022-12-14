package com.baec23.hobbybank.ui.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.navigation.NavService
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navService: NavService,
    private val snackbarService: SnackbarService
) : ViewModel() {
    private val _formState: MutableState<LoginFormState> = mutableStateOf(LoginFormState())
    val formState: State<LoginFormState> = _formState

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged -> {
                _formState.value = _formState.value.copy(username = event.username)
            }

            is LoginUiEvent.PasswordChanged -> {
                _formState.value = _formState.value.copy(password = event.password)
            }

            LoginUiEvent.LoginPressed -> {
                snackbarService.showSnackbar("Login Pressed!")
                navService.navigate(CONTENT_NAV_GRAPH_ROUTE)
            }

            LoginUiEvent.SignUpPressed -> {
                navService.navigate(NavScreen.Signup)
            }
        }
    }
}

data class LoginFormState(
    val username: String = "",
    val password: String = ""
)

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object LoginPressed : LoginUiEvent()
    object SignUpPressed : LoginUiEvent()

}