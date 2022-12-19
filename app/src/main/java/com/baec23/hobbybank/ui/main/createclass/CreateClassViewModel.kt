package com.baec23.hobbybank.ui.main.createclass

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.model.HobbyClassSession
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.repository.UserRepository
import com.baec23.hobbybank.service.NavService
import com.baec23.hobbybank.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val navService: NavService,
    private val snackbarService: SnackbarService,
    private val userRepository: UserRepository,
    private val hobbyClassRepository: HobbyClassRepository
) : ViewModel() {
    private val TAG: String = "CreateClassViewModel: "

    private val _selectedTabIndex: MutableState<Int> = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    //Tab1
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

    //Tab2
    private val _detailsFormState: MutableState<DetailsFormState> = mutableStateOf(
        DetailsFormState(
            minStudents = "",
            maxStudents = "",
            location = "",
            durationMinutes = "",
            details = ""
        )
    )
    val detailsFormState: State<DetailsFormState> = _detailsFormState

    //Tab3
    private val _addedSessions: MutableState<List<HobbyClassSession>> = mutableStateOf(listOf())
    val addedSessions: State<List<HobbyClassSession>> = _addedSessions

    private val _selectedDate: MutableState<String> = mutableStateOf("")
    val selectedDate: State<String> = _selectedDate

    private val _selectedTime: MutableState<String> = mutableStateOf("")
    val selectedTime: State<String> = _selectedTime

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
        var formState by _detailsFormState
        when (event) {
            is CreateClass2UiEvent.AmenityStatusChanged -> TODO()
            is CreateClass2UiEvent.DurationChanged -> formState =
                formState.copy(durationMinutes = event.durationInMinutes)

            is CreateClass2UiEvent.DetailsChanged -> formState =
                formState.copy(details = event.details)

            is CreateClass2UiEvent.LocationChanged -> formState =
                formState.copy(location = event.location)

            is CreateClass2UiEvent.MaxStudentsChanged -> formState =
                formState.copy(maxStudents = event.maxStudents)

            is CreateClass2UiEvent.MinStudentsChanged -> formState =
                formState.copy(minStudents = event.minStudents)

            CreateClass2UiEvent.NextPressed -> _selectedTabIndex.value++
            CreateClass2UiEvent.PrevPressed -> _selectedTabIndex.value--
        }
    }

    fun onEvent(event: CreateClass3UiEvent) {
        when (event) {
            is CreateClass3UiEvent.SelectedDateChanged -> _selectedDate.value = event.date
            is CreateClass3UiEvent.SelectedTimeChanged -> _selectedTime.value = event.time
            CreateClass3UiEvent.AddSessionPressed -> {
                if (_selectedDate.value.isBlank() || _selectedTime.value.isBlank())
                    return
                val mutableAddedSessions = _addedSessions.value.toMutableList()
                val endTime = LocalTime.parse(_selectedTime.value)
                    .plusMinutes(_detailsFormState.value.durationMinutes.toLong())
                mutableAddedSessions.add(
                    HobbyClassSession(
                        date = _selectedDate.value,
                        startTime = _selectedTime.value,
                        endTime = endTime.toString()
                    )
                )
                _addedSessions.value = mutableAddedSessions.toList()
                _selectedDate.value = ""
                _selectedTime.value = ""
            }

            CreateClass3UiEvent.PrevPressed -> _selectedTabIndex.value--
            CreateClass3UiEvent.FinishPressed -> {
                val newHobbyClass = HobbyClass(
                    name = (_classNameInputState.value as InputFormState.Valid).text,
                    details = detailsFormState.value.details,
                    location = detailsFormState.value.location,
                    creatorUserId = userRepository.currUser?.id!!,
                )
                viewModelScope.launch {
                    val result = hobbyClassRepository.saveHobbyClass(newHobbyClass, addedImages.value)
                    val savedClassId = result.getOrNull()?.id
                    for (session in addedSessions.value) {
                        hobbyClassRepository.saveHobbyClassSession(session.copy(classId = savedClassId!!))
                    }
                    snackbarService.showSnackbar("수업 등록 완료")
                    navService.navigate(NavScreen.MyAccount)
                }
            }
        }
    }

    private fun updateClassNameInputState(value: String) {

        if (value.isBlank())
            _classNameInputState.value = InputFormState.Empty
        else
            _classNameInputState.value = InputFormState.Valid(value)
    }
}

data class DetailsFormState(
    val minStudents: String,
    val maxStudents: String,
    val location: String,
    val durationMinutes: String,
    val details: String,
)

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
    data class MinStudentsChanged(val minStudents: String) : CreateClass2UiEvent()
    data class MaxStudentsChanged(val maxStudents: String) : CreateClass2UiEvent()
    data class DurationChanged(val durationInMinutes: String) : CreateClass2UiEvent()
    data class AmenityStatusChanged(val amenity: Amenity, val value: Boolean) :
        CreateClass2UiEvent()

    data class DetailsChanged(val details: String) : CreateClass2UiEvent()
    object PrevPressed : CreateClass2UiEvent()
    object NextPressed : CreateClass2UiEvent()
}

sealed class CreateClass3UiEvent {
    data class SelectedDateChanged(val date: String) : CreateClass3UiEvent()
    data class SelectedTimeChanged(val time: String) : CreateClass3UiEvent()
    object AddSessionPressed : CreateClass3UiEvent()
    object PrevPressed : CreateClass3UiEvent()
    object FinishPressed : CreateClass3UiEvent()
}

data class Amenity(
    val name: String,
    val status: Boolean,
    val icon: ImageVector? = null
)