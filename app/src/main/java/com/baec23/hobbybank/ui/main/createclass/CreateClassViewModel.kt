package com.baec23.hobbybank.ui.main.createclass

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val hobbyClassRepository: HobbyClassRepository,
    private val snackbarService: SnackbarService,
) : ViewModel() {

    private val _formState: MutableState<CreateClassFormState> =
        mutableStateOf(CreateClassFormState())
    val formState: State<CreateClassFormState> = _formState

    private val _addedImages: MutableState<List<Bitmap>> = mutableStateOf(listOf())
    val addedImages: State<List<Bitmap>> = _addedImages

    fun onEvent(event: CreateClassUiEvent) {
        when (event) {
            is CreateClassUiEvent.NameChanged -> {
                _formState.value = _formState.value.copy(name = event.name)
            }

            is CreateClassUiEvent.DetailsChanged -> {
                _formState.value =
                    _formState.value.copy(details = event.details)
            }

            is CreateClassUiEvent.ImageAdded -> {
                val newList = _addedImages.value.toMutableList()
                newList.add(event.bitmap)
                _addedImages.value = newList.toList()
            }

            is CreateClassUiEvent.ImageRemoved -> {
                val newList = _addedImages.value.toMutableList()
                newList.remove(event.bitmap)
                _addedImages.value = newList.toList()
                snackbarService.showSnackbar("Image Removed!", 1000)
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

data class CreateClassFormState(
    val name: String = "",
    val details: String = ""
)

sealed class CreateClassUiEvent {
    data class NameChanged(val name: String) : CreateClassUiEvent()
    data class DetailsChanged(val details: String) : CreateClassUiEvent()
    data class ImageAdded(val bitmap: Bitmap) : CreateClassUiEvent()
    data class ImageRemoved(val bitmap: Bitmap) : CreateClassUiEvent()
    object CreatePressed : CreateClassUiEvent()
}