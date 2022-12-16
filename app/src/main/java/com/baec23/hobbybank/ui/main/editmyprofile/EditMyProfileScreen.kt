package com.baec23.hobbybank.ui.main.editmyprofile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditMyProfileScreen(
    viewModel: EditMyProfileViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize(0.9f)){
        Text("Edit My Profile")
    }
}