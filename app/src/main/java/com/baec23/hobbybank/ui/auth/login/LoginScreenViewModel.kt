package com.baec23.hobbybank.ui.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val TAG = "LoginScreenViewModel: "

    private val _formState: MutableState<LoginFormState> = mutableStateOf(LoginFormState())
    private val _isFormValid: MutableState<Boolean> = mutableStateOf(false)
    val formState: State<LoginFormState> = _formState
    val isFormValid: State<Boolean> = _isFormValid

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged -> {
                _formState.value = _formState.value.copy(
                    username = event.username
                )
            }
            is LoginUiEvent.PasswordChanged -> {
                _formState.value = _formState.value.copy(
                    password = event.password
                )
            }
            LoginUiEvent.SubmitPressed -> {
                viewModelScope.launch {
                    if (!isFormValid.value){
                        Log.d(TAG, "form is wrong")
                    }else{
                        val result = userRepository.tryLogin(_formState.value.username, _formState.value.password)
                        if (result.isSuccess){
                            Log.d(TAG, "login success")
                        }else{
                            _formState.value=_formState.value.copy(
                                username = "",
                                password = ""
                            )
                        }
                    }
                }
            }
        }
        checkIfFormIsValid()
    }

    private fun checkIfFormIsValid() {
        val form by formState
        var isValid = true
        if (form.username.isEmpty()) isValid = false
        else if (form.password.isEmpty()) isValid = false
        _isFormValid.value = isValid
    }

}