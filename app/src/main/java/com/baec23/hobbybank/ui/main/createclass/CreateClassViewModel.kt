package com.baec23.hobbybank.ui.main.createclass

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.repository.HobbyClassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val hobbyClassRepository: HobbyClassRepository,
) : ViewModel() {
    private val _formState: MutableState<CreateClassFormState> =
        mutableStateOf(CreateClassFormState())
    val formState: State<CreateClassFormState> = _formState

    fun onEvent(event: CreateClassUiEvent) {
        when (event) {
            is CreateClassUiEvent.NameChanged -> {
                _formState.value = _formState.value.copy(name = event.name)
            }

            is CreateClassUiEvent.DetailsChanged -> {
                _formState.value =
                    _formState.value.copy(details = event.details)
            }

            CreateClassUiEvent.CreatePressed -> {
                viewModelScope.launch {
                    hobbyClassRepository.saveHobbyClass(
                        HobbyClass(
                            _formState.value.name,
                            _formState.value.details
                        )
                    )
                }

            }
        }
    }
}