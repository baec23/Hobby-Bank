package com.baec23.hobbybank.ui.auth.signup

sealed class SignupUiEvent {
    data class UsernameChanged(val username: String) : SignupUiEvent()
    data class Password1Changed(val password1: String) : SignupUiEvent()
    data class Password2Changed(val password2: String) : SignupUiEvent()
    data class DisplayNameChanged(val displayName: String) : SignupUiEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : SignupUiEvent()

    object SubmitPressed : SignupUiEvent()
}
