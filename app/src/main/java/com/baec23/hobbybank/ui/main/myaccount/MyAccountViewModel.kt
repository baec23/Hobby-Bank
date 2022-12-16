package com.baec23.hobbybank.ui.main.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.service.NavService
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    userRepository: UserRepository,
    hobbyClassRepository: HobbyClassRepository,
    private val navService: NavService
) : ViewModel() {

    val currUser = userRepository.currUser
    val myHobbyClasses: StateFlow<List<HobbyClass>> = hobbyClassRepository.getAllHobbyClasses()
        .map {
            it.filter { hobbyClass ->
                hobbyClass.creatorUserId == currUser?.id
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun onEvent(event: MyAccountUiEvent) {
        when (event) {
            is MyAccountUiEvent.ClassSelected -> TODO()
            MyAccountUiEvent.CreateNewClassPressed -> navService.navigate(NavScreen.CreateClass)
            MyAccountUiEvent.EditMyProfilePressed -> navService.navigate(NavScreen.EditMyProfile)
        }
    }
}

sealed class MyAccountUiEvent {
    data class ClassSelected(val selectedClass: HobbyClass) : MyAccountUiEvent()
    object CreateNewClassPressed : MyAccountUiEvent()
    object EditMyProfilePressed: MyAccountUiEvent()
}
