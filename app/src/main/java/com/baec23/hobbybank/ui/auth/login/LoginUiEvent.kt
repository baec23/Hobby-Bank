package com.baec23.hobbybank.ui.auth.login


sealed class LoginUiEvent{
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()

    object SubmitPressed : LoginUiEvent()
}
