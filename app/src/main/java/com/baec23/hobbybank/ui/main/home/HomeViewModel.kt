package com.baec23.hobbybank.ui.main.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.service.NavService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.collections.contains as contains1

@HiltViewModel
class HomeViewModel @Inject constructor(
    hobbyClassRepository: HobbyClassRepository,
    private val navService: NavService,
) : ViewModel() {
    private val _textFieldValue: MutableState<String> = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    val allHobbyClassList: StateFlow<List<HobbyClass?>> =
        hobbyClassRepository.getAllHobbyClasses()
            .stateIn(viewModelScope, SharingStarted.Lazily, listOf())


    var filteredList: StateFlow<List<HobbyClass?>> =
        if (textFieldValue.value.isBlank()) {
            hobbyClassRepository.getAllHobbyClasses()
                .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
        } else {
            Log.d("DEBUG", "asdfasdfasdfasdf")
            hobbyClassRepository.getAllHobbyClasses().map {
                it.filter { myHobbyClass ->
                    myHobbyClass.name.contains(_textFieldValue.value)
                }
            }
                .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
        }


    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchTextChanged -> {
                _textFieldValue.value = event.textFieldValue
            }

            is HomeUiEvent.HobbyClassClicked -> {
                navService.navigate(NavScreen.ViewClassDetails)
            }
            HomeUiEvent.SearchTextCleared -> {
                _textFieldValue.value = ""
            }
        }
    }

}

sealed class HomeUiEvent {
    data class SearchTextChanged(val textFieldValue: String) : HomeUiEvent()
    data class HobbyClassClicked(val hobbyClass: HobbyClass) : HomeUiEvent()
    object SearchTextCleared : HomeUiEvent()
}