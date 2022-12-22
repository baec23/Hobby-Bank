package com.baec23.hobbybank.ui.main.viewclassdetails

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "ViewClassDetailsViewModel"
@HiltViewModel
class ViewClassDetailsViewModel @Inject constructor() : ViewModel() {
    private val _toggleableItems: MutableState<List<ToggleableIconData>> = mutableStateOf(
        listOf(
            ToggleableIconData(name = "face", icon = Icons.Default.Face, label = "얼굴"),
            ToggleableIconData(name = "call", icon = Icons.Default.Call, label = "전화"),
            ToggleableIconData(name = "info", icon = Icons.Default.Info, label = "정보"),
            ToggleableIconData(name = "email", icon = Icons.Default.Email, label = "이메일"),
            ToggleableIconData(name = "home", icon = Icons.Default.Home, label = "홈"),
            ToggleableIconData(name = "cart", icon = Icons.Default.ShoppingCart),
            ToggleableIconData(name = "delete", icon = Icons.Default.Delete),
            ToggleableIconData(name = "add", icon = Icons.Default.Add),
            ToggleableIconData(name = "lock", icon = Icons.Default.Lock),
        )
    )
    val toggleableItems: State<List<ToggleableIconData>> = _toggleableItems
    fun onEvent(event: ViewClassDetailsUiEvent) {
        when (event) {
            is ViewClassDetailsUiEvent.OnIconToggled -> {
                val mutableItems = _toggleableItems.value.toMutableList()
                mutableItems[event.toggledIconIndex] = mutableItems[event.toggledIconIndex].doToggle()
                _toggleableItems.value = mutableItems.toList()
            }
        }
    }
}
sealed class ViewClassDetailsUiEvent {
    data class OnIconToggled(val toggledIconIndex: Int) : ViewClassDetailsUiEvent()
}