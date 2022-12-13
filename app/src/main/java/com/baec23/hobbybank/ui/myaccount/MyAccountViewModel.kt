package com.baec23.hobbybank.ui.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.model.User
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
    hobbyClassRepository: HobbyClassRepository
) : ViewModel() {
    val currUser: User = userRepository.currUser
    val myHobbyClasses: StateFlow<List<HobbyClass>> = hobbyClassRepository.getAllHobbyClasses()
        .map {
            it.filter { hobbyClass ->
                (hobbyClass?.creatorUserId ?: "") == currUser.id
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
}