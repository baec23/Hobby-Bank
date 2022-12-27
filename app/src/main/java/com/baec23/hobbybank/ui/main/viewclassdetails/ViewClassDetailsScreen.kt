package com.baec23.hobbybank.ui.main.viewclassdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.button.ButtonState
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.button.StatefulButton
import com.baec23.hobbybank.ui.comp.inputfield.InputValidator
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.section.ExpandableDisplaySection2
import com.baec23.hobbybank.ui.comp.togglable.Toggleable
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIcon
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconColumn
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconHorizontalGrid
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconRow
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconVerticalGrid

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ViewClassDetailsScreen(
    viewModel: ViewClassDetailsViewModel = hiltViewModel()
) {
    val toggleableItems by viewModel.toggleableItems

    var isDialogShown by remember { mutableStateOf(false) }
    var isAlertDialogShown by remember { mutableStateOf(false) }

    if (isDialogShown) {
        Dialog(
            onDismissRequest = { isDialogShown = false },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
                usePlatformDefaultWidth = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .background(Color.LightGray),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Hmm I don't know where this will be")
            }
        }
    }

    if (isAlertDialogShown) {
        AlertDialog(
            onDismissRequest = { isAlertDialogShown = false },
            confirmButton = {
                TextButton(onClick = { isAlertDialogShown = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = null,
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            title = { Text("This is my title") },
            text = { Text("The quick brown fox jumps over the lazy dog") },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
                usePlatformDefaultWidth = true
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        val radioOptions = listOf(
            "Color",
            "ColorBurn",
            "ColorDodge",
            "Darken",
            "Difference",
            "Dst",
            "DstAtop",
            "DstIn",
            "DstOut",
            "DstOver",
            "Exclusion",
            "Hardlight",
            "Hue",
            "Lighten",
            "Luminosity",
            "Modulate",
            "Multiply",
            "Overlay",
            "Plus",
            "Saturation",
            "Screen",
            "Softlight",
            "Src",
            "SrcAtop",
            "SrcIn",
            "SrcOut",
            "SrcOver",
            "Xor",
        )
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        DisplaySection(headerText = "Toggleable Test") {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .verticalScroll(state = rememberScrollState())
                .selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            var isToggled by remember { mutableStateOf(false) }
            Toggleable(isToggled = isToggled, onToggle = {
                isToggled = !isToggled
            }) {
                Icon(
                    modifier = Modifier.size(200.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null
                )
            }

        }


        var textValue1 by remember { mutableStateOf("") }
        var textValue2 by remember { mutableStateOf("") }
        var numberValue by remember { mutableStateOf("") }
        var hasError by remember { mutableStateOf(false) }
        var errorMessage: String? by remember { mutableStateOf(null) }

        DisplaySection(headerText = "InputField Test") {
            TextInputField(
                value = textValue1,
                onValueChange = {
                    if (it.contains("hello")) {
                        hasError = true
                        errorMessage = "Must not contain 'hello'"
                    } else {
                        hasError = false
                    }
                    textValue1 = it
                },
                label = "TextInputField TextNoSpaces",
                inputValidator = InputValidator.TextNoSpaces,
                placeholder = "Can only contain text... no spaces",
                hasError = hasError,
                errorMessage = errorMessage
            )

            TextInputField(
                value = textValue2,
                onValueChange = {
                    if (it.contains("hello")) {
                        hasError = true
                        errorMessage = "Must not contain 'hello'"
                    } else {
                        hasError = false
                    }
                    textValue2 = it
                },
                label = "Multiline TextInputField TextWithSpaces",
                placeholder = "Can only contain text or spaces",
                minLines = 2,
                maxLines = 4,
            )

        }

        var isExpanded by remember { mutableStateOf(false) }
        ExpandableDisplaySection2(
            isExpanded = isExpanded,
            onExpand = { isExpanded = !isExpanded },
            headerText = "Expandable Section And Button Test",
            headerSubtext = "Hello this is an expandable section with a long ass subtext",
        ) {
            var buttonState by remember { mutableStateOf(ButtonState.Enabled) }
            ToggleableIconRow(
                toggleableIconDataList = toggleableItems,
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(toggleableIconData = it)
            }
            Row {
                StatefulButton(onClick = {}, state = buttonState) {
                    Text("Hello")
                }
                StatefulButton(text = "Change Button State") {
                    buttonState = when (buttonState) {
                        ButtonState.Enabled -> ButtonState.Disabled
                        ButtonState.Disabled -> ButtonState.Loading
                        ButtonState.Loading -> ButtonState.Enabled
                    }
                }
            }

            Text("Lalalala")
            Text("Lalalalasdfa")
            Text("Lalalfdsafsadfala")
            Text("Lalasdfasdfalala")
            Text("Lalalasdfasdfala")
        }

        DisplaySection(headerText = "Dialog/Popup Test") {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                HBButton(onClick = { isDialogShown = true }, text = "Show Dialog")
                HBButton(onClick = { isAlertDialogShown = true }, text = "Show AlertDialog")
            }
        }

        DisplaySection(headerText = "Row") {
            ToggleableIconRow(
                toggleableIconDataList = toggleableItems,
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(toggleableIconData = it)
            }
        }
        DisplaySection(headerText = "Horizontal Grid") {
            ToggleableIconHorizontalGrid(
                modifier = Modifier.height(300.dp),
                toggleableIconDataList = toggleableItems,
                rows = GridCells.Fixed(2),
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(
                    toggleableIconData = it,
                    toggledOffSizePercent = 0.5f,
                    toggledOnColor = MaterialTheme.colorScheme.primary
                )
            }
        }

        DisplaySection(headerText = "Column") {
            ToggleableIconColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                toggleableIconDataList = toggleableItems,
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(
                    size = 200.dp,
                    toggleableIconData = it,
                    toggledOffSizePercent = 0.8f,
                    toggledOnColor = MaterialTheme.colorScheme.primary
                )
            }
        }
        DisplaySection(headerText = "Vertical Grid") {
            ToggleableIconVerticalGrid(
                modifier = Modifier.height(300.dp),
                toggleableIconDataList = toggleableItems,
                columns = GridCells.Fixed(2),
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(toggleableIconData = it)
            }
        }
    }
}

@Preview
@Composable
private fun ViewClassDetailsScreenPreview() {
    ViewClassDetailsScreen()
}