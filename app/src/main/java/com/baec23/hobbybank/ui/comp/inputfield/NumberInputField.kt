package com.baec23.hobbybank.ui.comp.inputfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeHolder: String = "",
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    imeAction: ImeAction = ImeAction.Default,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge,
        value = value,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Number
        ),
        onValueChange = { onValueChange(getValidatedInput(it)) },
        singleLine = true,
        minLines = minLines,
        maxLines = maxLines,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        },
    )
}

private fun getValidatedInput(s: String): String {
    val pattern = Regex("\\D")
    return s.replace(pattern, "")
}