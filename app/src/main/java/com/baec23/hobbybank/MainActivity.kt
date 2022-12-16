package com.baec23.hobbybank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.baec23.hobbybank.ui.app.App
import com.baec23.hobbybank.ui.theme.HobbyBankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HobbyBankTheme {
                App()
            }
        }
    }
}
