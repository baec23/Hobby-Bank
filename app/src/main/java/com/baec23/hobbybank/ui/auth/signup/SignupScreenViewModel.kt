package com.baec23.hobbybank.ui.auth.signup

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
class SignupScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val TAG = "SignupScreenViewModel: "

    private val _formState: MutableState<SignupFormState> = mutableStateOf(SignupFormState())
    private val _isFormValid: MutableState<Boolean> = mutableStateOf(false)
    val formState: State<SignupFormState> = _formState
    val isFormValid: State<Boolean> = _isFormValid

    fun onEvent(event: SignupUiEvent) {
        when (event) {

            is SignupUiEvent.UsernameChanged -> {
                _formState.value = _formState.value.copy(
                    username = event.username
                )
            }
            is SignupUiEvent.Password1Changed -> {
                _formState.value = _formState.value.copy(
                    password1 = event.password1
                )
            }
            is SignupUiEvent.Password2Changed -> {
                _formState.value = _formState.value.copy(
                    password2 = event.password2
                )
            }
            is SignupUiEvent.DisplayNameChanged -> {
                _formState.value = _formState.value.copy(
                    displayName = event.displayName
                )
            }
            is SignupUiEvent.PhoneNumberChanged -> {
                _formState.value = _formState.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            SignupUiEvent.SubmitPressed -> {
                Log.d(TAG, "onEvent: Submit Pressed")
                viewModelScope.launch {
                    userRepository.getId()

                    userRepository.saveUser(
                        SignupFormState(
                            _formState.value.username,
                            _formState.value.password1,
                            _formState.value.displayName,
                            _formState.value.phoneNumber
                        )
                    )
                }
            }
        }
        checkIfFormIsValid()

    }

    private fun checkIfFormIsValid(){
        val form by formState
        var isValid = true
        if (form.username.isEmpty()) isValid = false
        else if (form.password1.isEmpty()) isValid = false
        else if (form.password2.isEmpty()) isValid = false
        else if (form.displayName.isEmpty()) isValid = false
        else if (form.phoneNumber.isEmpty()) isValid = false
        else if (form.password1 != form.password2) {
            isValid = false
        }
        _isFormValid.value = isValid
    }
}