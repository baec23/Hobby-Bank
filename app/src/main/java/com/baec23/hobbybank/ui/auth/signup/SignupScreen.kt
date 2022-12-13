package com.baec23.hobbybank.ui.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: SignupScreenViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password1 = formState.password1
    val password2 = formState.password2
    val displayName = formState.displayName
    val phoneNumber = formState.phoneNumber
    val isFormValid by viewModel.isFormValid

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "SIGN UP",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {
                    viewModel.onEvent(SignupUiEvent.UsernameChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "ID") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password1,
                onValueChange = {
                    viewModel.onEvent(SignupUiEvent.Password1Changed(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "PASSWORD") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password2,
                onValueChange = {
                    viewModel.onEvent(SignupUiEvent.Password2Changed(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "PASSWORD CHECK") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = displayName,
                onValueChange = {
                    viewModel.onEvent(SignupUiEvent.DisplayNameChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "NAME") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    viewModel.onEvent(SignupUiEvent.PhoneNumberChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text(text = "PHONE NUMBER") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.onEvent(SignupUiEvent.SubmitPressed)
                },
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.wrapContentSize(),
                enabled = isFormValid
            ) {
                Text(text = "SUBMIT")
            }
        }
    }
}