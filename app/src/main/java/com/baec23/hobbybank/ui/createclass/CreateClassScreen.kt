package com.baec23.hobbybank.ui.createclass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClassScreen(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val currName = formState.name
    val currDetails = formState.details
    Column {
        Text("Class Name")
        TextField(
            value = currName,
            onValueChange = {
                viewModel.onEvent(CreateClassUiEvent.NameChanged(it))
            })
        Spacer(modifier = Modifier.height(10.dp))
        Text("Class Details")
        TextField(
            value = currDetails,
            onValueChange = {
                viewModel.onEvent(CreateClassUiEvent.DetailsChanged(it))
            })
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { viewModel.onEvent(CreateClassUiEvent.CreatePressed) }) {
            Text("Create")
        }
    }
}