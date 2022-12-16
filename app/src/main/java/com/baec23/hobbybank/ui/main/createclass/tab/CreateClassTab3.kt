package com.baec23.hobbybank.ui.main.createclass.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.main.createclass.CreateClassViewModel

@Composable
fun CreateClassTab3(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Text("Placeholder")
    }
}