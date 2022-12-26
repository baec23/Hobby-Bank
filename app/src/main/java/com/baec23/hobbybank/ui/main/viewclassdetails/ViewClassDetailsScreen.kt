package com.baec23.hobbybank.ui.main.viewclassdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.button.ButtonState
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.button.HBButton3
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.section.ExpandableDisplaySection
import com.baec23.hobbybank.ui.comp.section.ExpandableDisplaySection2
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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        ExpandableDisplaySection2(
            isExpanded = isExpanded,
            onExpand = { isExpanded = !isExpanded },
            headerText = "Expandable Section Test",
            headerSubtext = "Hello this is an expandable section with a long ass subtext",
        ) {
            ToggleableIconRow(
                toggleableIconDataList = toggleableItems,
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(toggleableIconData = it)
            }
            HBButton3(
                text = "Test Button",
                onClick = {},
            )
            Text("Lalalala")
            Text("Lalalalasdfa")
            Text("Lalalfdsafsadfala")
            Text("Lalasdfasdfalala")
            Text("Lalalasdfasdfala")
        }


        DisplaySection(headerText = "Button Test") {
            var buttonState by remember { mutableStateOf(ButtonState.Idle) }
            HBButton3(
                text = "Test Button 3",
                onClick = {
                    buttonState =
                        if (buttonState != ButtonState.Loading) ButtonState.Loading else ButtonState.Idle
                }, state = buttonState
            )
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