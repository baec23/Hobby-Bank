package com.baec23.hobbybank.ui.viewclasses

import com.baec23.hobbybank.model.HobbyClass

sealed class ViewClassesUiEvent {
    data class ClassClicked(val hobbyClass: HobbyClass): ViewClassesUiEvent()
}