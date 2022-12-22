package com.baec23.hobbybank.ui.main.viewclassdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIcon
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconColumn
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconHorizontalGrid
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconRow
import com.baec23.hobbybank.ui.comp.togglable.ToggleableIconVerticalGrid

@Composable
fun ViewClassDetailsScreen(
    viewModel: ViewClassDetailsViewModel = hiltViewModel()
) {
    val toggleableItems by viewModel.toggleableItems

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        DisplaySection(headerText = "Row") {
            ToggleableIconRow(
                toggleableIconDataList = toggleableItems,
                onItemToggle = { viewModel.onEvent(ViewClassDetailsUiEvent.OnIconToggled(it)) }) {
                ToggleableIcon(toggleableIconData = it)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

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