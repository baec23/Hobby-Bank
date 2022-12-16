package com.baec23.hobbybank.ui.main.createclass

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.main.createclass.tab.CreateClassTab3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClassScreen(
    viewModel: CreateClassViewModel = hiltViewModel(),
) {
    val tabTitles = listOf(
        "단계 1",
        "단계 2",
        "단계 3"
    )
    val selectedTabIndex by viewModel.selectedTabIndex

    BackHandler(
        enabled = selectedTabIndex > 0,
        onBack = {
            viewModel.onEvent(CreateClassUiEvent.BackPressed)
        })

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            divider = { Divider(thickness = 1.dp) }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.tertiary,
                    onClick = { viewModel.onEvent(CreateClassUiEvent.TabSelected(index)) }) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
        Box(modifier = Modifier.padding(8.dp))
        {
            when (selectedTabIndex) {
                0 -> CreateClassTab1()
                1 -> CreateClassTab2()
                2 -> CreateClassTab3()
            }
        }
    }

    //endregion
}


@Preview
@Composable
fun CreateClassScreenPreview() {
    CreateClassScreen()
}