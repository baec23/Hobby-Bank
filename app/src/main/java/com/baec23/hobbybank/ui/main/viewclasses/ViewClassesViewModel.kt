package com.baec23.hobbybank.ui.main.viewclasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.repository.HobbyClassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewClassesViewModel @Inject constructor(
    hobbyClassRepository: HobbyClassRepository,
) : ViewModel() {

    val allHobbyClasses: StateFlow<List<HobbyClass?>> =
        hobbyClassRepository.getAllHobbyClasses()
            .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun onEvent(event: ViewClassesUiEvent) {
        when (event) {
            is ViewClassesUiEvent.ClassClicked -> {
                viewModelScope.launch {
                }
            }
        }
    }
}