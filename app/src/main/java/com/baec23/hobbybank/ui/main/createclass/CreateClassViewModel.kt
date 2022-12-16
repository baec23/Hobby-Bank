package com.baec23.hobbybank.ui.main.createclass

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.service.NavService
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val navService: NavService,
    private val snackbarService: SnackbarService,
    private val hobbyClassRepository: HobbyClassRepository
) : ViewModel() {
    private val _selectedTabIndex: MutableState<Int> = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    private val _addedImages: MutableState<List<Bitmap>> = mutableStateOf(listOf())
    val addedImages: State<List<Bitmap>> = _addedImages

    private val _classNameInputState: MutableState<InputFormState> =
        mutableStateOf(InputFormState.Empty)
    val classNameInputState: State<InputFormState> = _classNameInputState

    private val _tagNameInputState: MutableState<InputFormState> =
        mutableStateOf(InputFormState.Empty)
    val tagNameInputState: State<InputFormState> = _tagNameInputState

    private val _addedTags: MutableState<List<String>> = mutableStateOf(listOf())
    val addedTags: State<List<String>> = _addedTags

    fun onEvent(event: CreateClassUiEvent) {
        when (event) {
            CreateClassUiEvent.BackPressed -> (_selectedTabIndex.value > 0).let { _selectedTabIndex.value-- }
            is CreateClassUiEvent.TabSelected -> _selectedTabIndex.value = event.tabIndex
        }
    }

    fun onEvent(event: CreateClass1UiEvent) {
        when (event) {
            is CreateClass1UiEvent.ImageAdded -> {
                val newList = _addedImages.value.toMutableList()
                newList.add(event.bitmap)
                _addedImages.value = newList.toList()
            }

            is CreateClass1UiEvent.ImageRemoved -> {
                val newList = _addedImages.value.toMutableList()
                newList.remove(event.bitmap)
                _addedImages.value = newList.toList()
                snackbarService.showSnackbar("Image Removed!", 1000)
            }

            is CreateClass1UiEvent.NameChanged -> updateClassNameInputState(event.name)
            CreateClass1UiEvent.NextPressed -> _selectedTabIndex.value++
            is CreateClass1UiEvent.TagAdded -> TODO()
            is CreateClass1UiEvent.TagRemoved -> TODO()
        }
    }

    fun onEvent(event: CreateClass2UiEvent) {
        when (event) {
            is CreateClass2UiEvent.AmenityStatusChanged -> TODO()
            is CreateClass2UiEvent.ClassDurationChanged -> TODO()
            is CreateClass2UiEvent.DetailsChanged -> TODO()
            is CreateClass2UiEvent.LocationChanged -> TODO()
            is CreateClass2UiEvent.MaxStudentsChanged -> TODO()
            is CreateClass2UiEvent.MinStudentsChanged -> TODO()
            CreateClass2UiEvent.NextPressed -> TODO()
            CreateClass2UiEvent.PrevPressed -> _selectedTabIndex.value--
        }
    }

    private fun updateClassNameInputState(value: String) {

        if (value.isBlank())
            _classNameInputState.value = InputFormState.Empty
        else
            _classNameInputState.value = InputFormState.Valid(value)
    }
}

sealed class InputFormState {
    object Empty : InputFormState()
    data class Valid(val text: String) : InputFormState()
    data class Invalid(val text: String, val error: String) : InputFormState()
}


sealed class CreateClassUiEvent {
    data class TabSelected(val tabIndex: Int) : CreateClassUiEvent()
    object BackPressed : CreateClassUiEvent()
}

sealed class CreateClass1UiEvent {
    data class NameChanged(val name: String) : CreateClass1UiEvent()
    data class ImageAdded(val bitmap: Bitmap) : CreateClass1UiEvent()
    data class ImageRemoved(val bitmap: Bitmap) : CreateClass1UiEvent()
    data class TagAdded(val tagName: String) : CreateClass1UiEvent()
    data class TagRemoved(val tagName: String) : CreateClass1UiEvent()
    object NextPressed : CreateClass1UiEvent()
}

sealed class CreateClass2UiEvent {
    data class LocationChanged(val location: String) : CreateClass2UiEvent()
    data class MinStudentsChanged(val minStudents: Int) : CreateClass2UiEvent()
    data class MaxStudentsChanged(val maxStudents: Int) : CreateClass2UiEvent()
    data class ClassDurationChanged(val durationInMinutes: Int) : CreateClass2UiEvent()
    data class AmenityStatusChanged(val amenity: Amenity, val value: Boolean) :
        CreateClass2UiEvent()

    data class DetailsChanged(val details: String) : CreateClass2UiEvent()
    object PrevPressed : CreateClass2UiEvent()
    object NextPressed : CreateClass2UiEvent()
}

data class Amenity(
    val name: String,
    val status: Boolean,
    val icon: ImageVector? = null
)