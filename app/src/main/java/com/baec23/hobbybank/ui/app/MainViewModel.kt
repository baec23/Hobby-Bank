package com.baec23.hobbybank.ui.app

import com.baec23.hobbybank.repository.DataStoreRepository
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.hobbybank.navigation.ContentNavScreen
import com.baec23.hobbybank.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _currNavScreen: MutableState<ContentNavScreen> = mutableStateOf(ContentNavScreen.Home)
    val currNavScreen: State<ContentNavScreen> = _currNavScreen


}