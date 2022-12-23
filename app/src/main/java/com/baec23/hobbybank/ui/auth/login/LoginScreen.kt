package com.baec23.hobbybank.ui.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
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
import com.baec23.hobbybank.ui.comp.inputfield.PasswordInputField
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.main.createclass.CreateClass2UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password = formState.password

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "LOGIN",
                fontSize = 30.sp,
            )
            TextInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = username,
                onValueChanged = { viewModel.onEvent(LoginUiEvent.UsernameChanged(it)) },
                label = "ID",
                placeholder = "ID를 입력해 주세요",
                imeAction = ImeAction.Next,
            )
            PasswordInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChanged = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
                label = "PASSWORD",
                placeholder = "PASSWORD를 입력해주세요",
                imeAction = ImeAction.Done
            )

//
//            TextInputField(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = password,
//                onValueChanged = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
//                label = "PASSWORD",
//                placeholder = "PASSWORD를 입력해주세요",
//                imeAction = ImeAction.Done
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HBButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.onEvent(LoginUiEvent.LoginPressed) },
                    text = "Login!"
                )
                HBButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.onEvent(LoginUiEvent.SignUpPressed) },
                    text ="Sign up!"
                )
            }


//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = username,
//                onValueChange = {
//                    viewModel.onEvent(LoginUiEvent.UsernameChanged(it))
//                },
//                label = { Text(text = "ID") },
//                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            OutlinedTextField(
//                value = password,
//                onValueChange = {
//                    viewModel.onEvent(LoginUiEvent.PasswordChanged(it))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = { Text(text = "PASSWORD") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                visualTransformation = PasswordVisualTransformation()
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            OutlinedButton(
//                onClick = { viewModel.onEvent(LoginUiEvent.LoginPressed) }) {
//                Text("Login!")
//            }
//            OutlinedButton(onClick = { viewModel.onEvent(LoginUiEvent.SignUpPressed) }) {
//                Text("Sign up!")
//            }
        }
    }
}

