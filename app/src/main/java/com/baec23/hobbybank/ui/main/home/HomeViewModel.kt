package com.baec23.hobbybank.ui.main.home

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    hobbyClassRepository: HobbyClassRepository,
    private val navService: NavService,
) : ViewModel() {

    private val _searchFieldText: MutableState<String> = mutableStateOf("")
    val searchFieldText: State<String> = _searchFieldText

    private val _filteredHobbyClassList: MutableState<List<HobbyClass>> = mutableStateOf(listOf())
    val filteredHobbyClassList = _filteredHobbyClassList

    private var allHobbyClassList: List<HobbyClass> = listOf()

    //public function

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchTextChanged -> {
                _searchFieldText.value = event.textFieldValue
                doFilter()
            }

            is HomeUiEvent.HobbyClassClicked -> {
                navService.navigate(NavScreen.ViewClassDetails)
            }

            HomeUiEvent.SearchTextCleared -> {
                _searchFieldText.value = ""
                doFilter()
            }
        }
    }
    init {
        viewModelScope.launch {
            hobbyClassRepository.getAllHobbyClasses().collectLatest {
                allHobbyClassList = it
                _filteredHobbyClassList.value = it
            }
        }
    }
    private fun doFilter() {
        if (_searchFieldText.value.isBlank())
            _filteredHobbyClassList.value = allHobbyClassList
        else {
            _filteredHobbyClassList.value =
                allHobbyClassList.filter { hobbyClass ->
                    (hobbyClass.name.contains(_searchFieldText.value) ||
                            hobbyClass.details.contains(_searchFieldText.value))
                }
        }
    }
}

sealed class HomeUiEvent {
    data class SearchTextChanged(val textFieldValue: String) : HomeUiEvent()
    data class HobbyClassClicked(val hobbyClass: HobbyClass) : HomeUiEvent()
    object SearchTextCleared : HomeUiEvent()
}