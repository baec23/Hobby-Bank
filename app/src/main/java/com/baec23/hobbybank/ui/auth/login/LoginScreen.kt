package com.baec23.hobbybank.ui.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password = formState.password

    LazyColumn(){
        items(5){

        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "LOGIN",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = username,
                onValueChange = {
                    viewModel.onEvent(LoginUiEvent.UsernameChanged(it))
                },
                label = { Text(text = "ID") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    viewModel.onEvent(LoginUiEvent.PasswordChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "PASSWORD") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = { viewModel.onEvent(LoginUiEvent.LoginPressed) }) {
                Text("Login!")
            }
            OutlinedButton(onClick = { viewModel.onEvent(LoginUiEvent.SignUpPressed) }) {
                Text("Sign up!")
            }
        }
    }
}

