package com.baec23.hobbybank.ui.auth.signup

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.User
import com.baec23.hobbybank.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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
                    val form by _formState
                    val user = User(
                        id = "",
                        username = form.username,
                        password = form.password1,
                        displayName = form.displayName,
                        phoneNumber = form.phoneNumber,
                        location = ""
                    )
                    userRepository.trySignup(user)
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
sealed class SignupUiEvent {
    data class UsernameChanged(val username: String) : SignupUiEvent()
    data class Password1Changed(val password1: String) : SignupUiEvent()
    data class Password2Changed(val password2: String) : SignupUiEvent()
    data class DisplayNameChanged(val displayName: String) : SignupUiEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : SignupUiEvent()
    object SubmitPressed : SignupUiEvent()
}