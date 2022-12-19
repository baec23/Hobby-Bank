package com.baec23.hobbybank.ui.main.viewclasses

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.navigation.NavScreen
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.service.NavService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewClassesViewModel @Inject constructor(
    hobbyClassRepository: HobbyClassRepository,
    private val navService: NavService
) : ViewModel() {

    private val _searchFieldText: MutableState<String> = mutableStateOf("")
    val searchFieldText: State<String> = _searchFieldText

    private var allHobbyClasses: List<HobbyClass>? = null

    private val _filteredHobbyClasses: MutableStateFlow<List<HobbyClass>> =
        MutableStateFlow(listOf())
    val filteredHobbyClasses: StateFlow<List<HobbyClass>> = _filteredHobbyClasses

    fun onEvent(event: ViewClassesUiEvent) {
        when (event) {
            is ViewClassesUiEvent.ClassClicked -> {
                navService.navigate(NavScreen.ViewClassDetails)
            }

            is ViewClassesUiEvent.SearchTextChanged -> {
                _searchFieldText.value = event.searchText
                doFilter()
            }
        }
    }

    private fun doFilter() {
        allHobbyClasses?.let {
            if (_searchFieldText.value.isBlank())
                _filteredHobbyClasses.value = it
            else
                _filteredHobbyClasses.value =
                    it.filter { hobbyClass -> hobbyClass.name.contains(_searchFieldText.value) }
        }
    }

    init {
        viewModelScope.launch {
            hobbyClassRepository.getAllHobbyClasses().collectLatest {
                allHobbyClasses = it
                _filteredHobbyClasses.value = it
            }
        }
    }
}

sealed class ViewClassesUiEvent {
    data class SearchTextChanged(val searchText: String) : ViewClassesUiEvent()
    data class ClassClicked(val hobbyClass: HobbyClass) : ViewClassesUiEvent()
}