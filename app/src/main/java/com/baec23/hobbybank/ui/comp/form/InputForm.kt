package com.baec23.hobbybank.ui.comp.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField2

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputForm(
    modifier: Modifier = Modifier,

    ) {
    val focusManager = LocalFocusManager.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextInputField2(
            value = "",
            onValueChanged = {},
            label = "Hello Label 1",
            placeholder = "Placeholder 1",
            onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            })
        TextInputField2(
            value = "",
            onValueChanged = {},
            label = "Hello Label 2",
            placeholder = "Placeholder 2",
            onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            })
        TextInputField2(
            value = "",
            onValueChanged = {},
            label = "Hello Label 3",
            placeholder = "Placeholder 3",
            onNext = {
                focusManager.clearFocus()
            })
    }


}