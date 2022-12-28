package com.baec23.hobbybank.ui.main.viewclassdetails

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
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "ViewClassDetailsViewModel"
@HiltViewModel
class ViewClassDetailsViewModel @Inject constructor() : ViewModel() {
    private val _toggleableItems: MutableState<List<ToggleableIconListItem>> = mutableStateOf(
        listOf(
            ToggleableIconListItem(name = "face", iconImageVector = Icons.Default.Face, label = "얼굴"),
            ToggleableIconListItem(name = "call", iconImageVector = Icons.Default.Call, label = "전화"),
            ToggleableIconListItem(name = "info", iconImageVector = Icons.Default.Info, label = "정보"),
            ToggleableIconListItem(name = "email", iconImageVector = Icons.Default.Email, label = "이메일"),
            ToggleableIconListItem(name = "home", iconImageVector = Icons.Default.Home, label = "홈"),
            ToggleableIconListItem(name = "cart", iconImageVector = Icons.Default.ShoppingCart),
            ToggleableIconListItem(name = "delete", iconImageVector = Icons.Default.Delete),
            ToggleableIconListItem(name = "add", iconImageVector = Icons.Default.Add),
            ToggleableIconListItem(name = "lock", iconImageVector = Icons.Default.Lock),
        )
    )
    val toggleableItems: State<List<ToggleableIconListItem>> = _toggleableItems
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