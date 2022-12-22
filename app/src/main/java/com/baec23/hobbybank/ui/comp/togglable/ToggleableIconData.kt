package com.baec23.hobbybank.ui.comp.togglable

import androidx.compose.ui.graphics.vector.ImageVector

data class ToggleableIconData(
    val name: String,
    val icon: ImageVector,
    val label: String? = null,
    val isToggled: Boolean = false,
) {
    fun doToggle(): ToggleableIconData {
        return this.copy(isToggled = !isToggled)
    }
}
