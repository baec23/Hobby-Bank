package com.baec23.hobbybank.ui.main.createclass
sealed class CreateClassUiEvent {
    data class NameChanged(val name: String) : CreateClassUiEvent()
    data class DetailsChanged(val details: String) : CreateClassUiEvent()
    object CreatePressed : CreateClassUiEvent()
}