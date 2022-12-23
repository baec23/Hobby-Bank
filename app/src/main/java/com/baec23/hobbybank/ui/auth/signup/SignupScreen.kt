package com.baec23.hobbybank.ui.auth.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.inputfield.NumberInputField
import com.baec23.hobbybank.ui.comp.inputfield.PasswordInputField
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: SignupScreenViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password1 = formState.password1
    val password2 = formState.password2
    val displayName = formState.displayName
    val phoneNumber = formState.phoneNumber
    val isFormValid by viewModel.isFormValid

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "SIGN UP",
                fontSize = 30.sp
            )
            TextInputField(
                value = username,
                onValueChanged = { viewModel.onEvent(SignupUiEvent.UsernameChanged(it)) },
                label = "ID",
                placeholder = "ID를 입력해주세요",
                imeAction = ImeAction.Next
            )
            PasswordInputField(
                value = password1,
                onValueChanged = { viewModel.onEvent(SignupUiEvent.Password1Changed(it)) },
                label = "PASSWORD",
                placeholder = "PASSWORD를 입력해주세요",
                imeAction = ImeAction.Next,)
//            TextInputField(
//                value = password1,
//                onValueChanged = { viewModel.onEvent(SignupUiEvent.Password1Changed(it)) },
//                label = "PASSWORD",
//                placeholder = "PASSWORD를 입력해주세요",
//                imeAction = ImeAction.Next,
//            )
            PasswordInputField(
                value = password2,
                onValueChanged = { viewModel.onEvent(SignupUiEvent.Password2Changed(it)) },
                label = "PASSWORD CHECK",
                placeholder = "PASSWORD를 다시 한 번 입력해주세요",
                imeAction = ImeAction.Next)
//            TextInputField(
//                value = password2,
//                onValueChanged = { viewModel.onEvent(SignupUiEvent.Password2Changed(it)) },
//                label = "PASSWORD CHECK",
//                placeholder = "PASSWORD를 다시 한 번 입력해주세요",
//                imeAction = ImeAction.Next
//            )
            TextInputField(
                value = displayName,
                onValueChanged = { viewModel.onEvent(SignupUiEvent.DisplayNameChanged(it)) },
                label ="NAME",
                placeholder = "NAME을 입력해주세요",
                imeAction = ImeAction.Next
            )
            NumberInputField(
                value = phoneNumber,
                label = "PHONE NUMBER",
                onValueChange = {viewModel.onEvent(SignupUiEvent.PhoneNumberChanged(it))},
                placeHolder = "PHONE NUMBER를 입력해주세요",
                imeAction = ImeAction.Done
            )
            HBButton(
                onClick = {
                    viewModel.onEvent(SignupUiEvent.SubmitPressed)
                },
                text = "SUBMIT!",
                enabled = isFormValid,
            )

//            OutlinedTextField(
//                value = username,
//                onValueChange = {
//                    viewModel.onEvent(SignupUiEvent.UsernameChanged(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "ID") }
//            )
//            OutlinedTextField(
//                value = password1,
//                onValueChange = {
//                    viewModel.onEvent(SignupUiEvent.Password1Changed(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "PASSWORD") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                visualTransformation = PasswordVisualTransformation()
//            )
//            OutlinedTextField(
//                value = password2,
//                onValueChange = {
//                    viewModel.onEvent(SignupUiEvent.Password2Changed(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "PASSWORD CHECK") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                visualTransformation = PasswordVisualTransformation()
//            )
//            OutlinedTextField(
//                value = displayName,
//                onValueChange = {
//                    viewModel.onEvent(SignupUiEvent.DisplayNameChanged(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "NAME") }
//            )
//            OutlinedTextField(
//                value = phoneNumber,
//                onValueChange = {
//                    viewModel.onEvent(SignupUiEvent.PhoneNumberChanged(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "PHONE NUMBER") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
//            )
//            Button(
//                onClick = {
//                    viewModel.onEvent(SignupUiEvent.SubmitPressed)
//                },
//                contentPadding = PaddingValues(10.dp),
//                modifier = Modifier.wrapContentSize(),
//                enabled = isFormValid
//            ) {
//                Text(text = "SUBMIT")
//            }
        }
    }
}