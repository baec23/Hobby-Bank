package com.baec23.hobbybank.ui.comp.inputfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: String? = null,
    hasError: Boolean = false,
    errorMessage: String? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    singleLine: Boolean = minLines == 1 && maxLines == 1,
    inputValidator: InputValidator = if (singleLine) InputValidator.TextNoSpaces else InputValidator.TextWithSpacesAndNewLine
) {
    InputField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        placeholder = placeholder,
        hasError = hasError,
        errorMessage = errorMessage,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        inputValidator = inputValidator
    )
}

@Preview
@Composable
fun TextInputFieldPreview() {
    TextInputField(value = "Hello", onValueChange = {}, label = "Label")
}