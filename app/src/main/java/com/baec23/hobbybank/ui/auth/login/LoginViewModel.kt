package com.baec23.hobbybank.ui.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.navigation.CONTENT_NAV_GRAPH_ROUTE
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.service.NavService
import com.baec23.hobbybank.repository.UserRepository
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val navService: NavService,
    private val snackbarService: SnackbarService
) : ViewModel() {

    private val TAG = "LoginViewModel: "
    private val _formState: MutableState<LoginFormState> = mutableStateOf(LoginFormState())
    val formState: State<LoginFormState> = _formState
    private val _isFormValid: MutableState<Boolean> = mutableStateOf(false)
    val isFormValid: State<Boolean> = _isFormValid

    private fun checkIfFormIsValid() {
        val form by _formState
        var isValid = true
        if (form.username.isEmpty()) isValid = false
        else if (form.password.isEmpty()) isValid = false
        _isFormValid.value = isValid
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged -> {
                _formState.value = _formState.value.copy(
                    username = event.username
                )
                checkIfFormIsValid()
            }

            is LoginUiEvent.PasswordChanged -> {
                _formState.value = _formState.value.copy(
                    password = event.password
                )
                checkIfFormIsValid()
            }

            LoginUiEvent.LoginPressed -> {
                viewModelScope.launch {
                    if (!isFormValid.value) {
                        snackbarService.showSnackbar("Form is wrong")
                    } else {
                        val result = userRepository.tryLogin(
                            _formState.value.username,
                            _formState.value.password
                        )

                        val myUser = result.getOrElse { exception ->
                            exception.message?.let {
                                snackbarService.showSnackbar(it)
                            }
                            _formState.value = _formState.value.copy(
                                username = "",
                                password = ""
                            )
                            return@launch
                        }
                        Log.d(TAG, "myUser: ${myUser.username} | ${myUser.id}")
                        snackbarService.showSnackbar("Login Successful")
                        navService.navigate(CONTENT_NAV_GRAPH_ROUTE, clearBackStack = true)
                    }
                }
            }

            LoginUiEvent.SignUpPressed -> navService.navigate(NavScreen.Signup)
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