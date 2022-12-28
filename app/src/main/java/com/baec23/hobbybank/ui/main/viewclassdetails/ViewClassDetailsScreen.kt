package com.baec23.hobbybank.ui.main.viewclassdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.baec23.hobbybank.ui.comp.button.ButtonState
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.button.StatefulButton
import com.baec23.hobbybank.ui.comp.fadinglazy.FadingLazyVerticalGrid
import com.baec23.hobbybank.ui.comp.inputfield.InputField
import com.baec23.hobbybank.ui.comp.inputfield.InputField2
import com.baec23.hobbybank.ui.comp.inputfield.InputValidator
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.section.ExpandableDisplaySection
import com.baec23.hobbybank.ui.comp.togglable.Toggleable
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconColumn
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconHorizontalGrid
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconRow
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconVerticalGrid
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.memberProperties

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
        var blendModes by remember { mutableStateOf(listOf<BlendMode>()) }
        var selectedBlendMode by remember { mutableStateOf(BlendMode.Clear) }

        LaunchedEffect(true) {
            val companionClass = BlendMode::class.companionObject?.objectInstance
            val blendModeKProperties = BlendMode::class.companionObject?.memberProperties
            blendModes = blendModeKProperties?.map {
                it.getter.call(companionClass) as BlendMode
            }!!
        }



        DisplaySection(headerText = "New Input Field Test") {
            var fieldValue by remember { mutableStateOf("") }
            var fieldHasError by remember { mutableStateOf(false) }
            var fieldErrorMessage: String? by remember { mutableStateOf(null) }

            InputField(
                value = fieldValue, onValueChange = {
                    if (it.contains(" ")) {
                        fieldHasError = true
                        fieldErrorMessage = "Cannot contain spaces"
                    } else {
                        fieldHasError = false
                    }
                    fieldValue = it
                },
                hasError = fieldHasError,
                errorMessage = fieldErrorMessage,
                placeholder = "This is a placeholder",
                inputValidator = InputValidator.TextNoSpaces,
                maxLines = 1
            )

            InputField2(
                value = fieldValue,
                onValueChange = {
                    if (it.contains(" ")) {
                        fieldHasError = true
                        fieldErrorMessage = "Cannot contain spaces"
                    } else {
                        fieldHasError = false
                    }
                    fieldValue = it
                },
                label = "InputField2 Test",
                placeholder = "This is placeholder text",
                hasError = fieldHasError,
                errorMessage = fieldErrorMessage
            )
        }

        DisplaySection(headerText = "Toggleable Test") {
            FadingLazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .selectableGroup(),
                columns = GridCells.Fixed(2)
            ) {
                items(blendModes.count()) {
                    val blendMode = blendModes[it]
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .selectable(
                                selected = (blendMode == selectedBlendMode),
                                onClick = { selectedBlendMode = blendMode },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (blendMode == selectedBlendMode),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = blendMode.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            var isToggled by remember { mutableStateOf(false) }
            Toggleable(
                modifier = Modifier.fillMaxWidth(),
                isToggled = isToggled,
                onToggle = { isToggled = !isToggled },
                blendMode = selectedBlendMode
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Hello!", style = MaterialTheme.typography.displayMedium)
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(200.dp),
                        model = "https://picsum.photos/1200",
                        contentScale = ContentScale.FillHeight,
                        loading = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        },
                        contentDescription = null,
                    )
                    Icon(
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )
                }
            }

        }


        DisplaySection(headerText = "InputField Test") {

            var textValue1 by remember { mutableStateOf("") }
            var textValue2 by remember { mutableStateOf("") }
            var numberValue by remember { mutableStateOf("") }
            var hasError by remember { mutableStateOf(false) }
            var errorMessage: String? by remember { mutableStateOf(null) }
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
        ExpandableDisplaySection(
            isExpanded = isExpanded,
            onExpand = { isExpanded = !isExpanded },
            headerText = "Expandable Section And Button Test",
            headerSubtext = "Hello this is an expandable section with a long ass subtext",
        ) {
            var buttonState by remember { mutableStateOf(ButtonState.Enabled) }

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

        var isToggleableIconListSectionExpanded by remember { mutableStateOf(false) }
        ExpandableDisplaySection(
            isExpanded = isToggleableIconListSectionExpanded,
            onExpand = {
                isToggleableIconListSectionExpanded = !isToggleableIconListSectionExpanded
            },
            headerText = "Toggleable Icon Lists"
        ) {
            DisplaySection(headerText = "Row") {
                ToggleableIconRow(
                    toggleableIconListItems = toggleableItems,
                    onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) })
            }
            DisplaySection(headerText = "Horizontal Grid") {
                ToggleableIconHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    toggleableIconListItems = toggleableItems,
                    rows = GridCells.Fixed(2),
                    onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) })
            }

            DisplaySection(headerText = "Column") {
                ToggleableIconColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    toggleableIconListItems = toggleableItems,
                    onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) })
            }
            DisplaySection(headerText = "Vertical Grid") {
                ToggleableIconVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    columns = GridCells.Fixed(2),
                    toggleableIconListItems = toggleableItems,
                    onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }
                )
            }
        }
    }
}

data class BlendModeViewer(
    val name: String,
    val blendMode: BlendMode
)

@Preview
@Composable
private fun ViewClassDetailsScreenPreview() {
    ViewClassDetailsScreen()
}