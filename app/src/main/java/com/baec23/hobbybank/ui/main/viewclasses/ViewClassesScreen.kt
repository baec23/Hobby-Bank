package com.baec23.hobbybank.ui.main.viewclasses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField

@Composable
fun ViewClassesScreen(
    viewModel: ViewClassesViewModel = hiltViewModel()
) {
    val allHobbyClasses by viewModel.filteredHobbyClasses.collectAsState()
    val searchFieldText by viewModel.searchFieldText

    Column(modifier = Modifier.fillMaxWidth()) {
        TextInputField(
            value = searchFieldText,
            onValueChanged = {
                viewModel.onEvent(ViewClassesUiEvent.SearchTextChanged(it))
            },
            label = "Search"
        )
        LazyColumn {
            items(count = allHobbyClasses.size) { index ->
                HobbyClassItem(modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                    hobbyClass = allHobbyClasses[index],
                    onClick = { viewModel.onEvent(ViewClassesUiEvent.ClassClicked(HobbyClass())) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyClassItem(
    modifier: Modifier = Modifier,
    hobbyClass: HobbyClass,
    onClick: (HobbyClass) -> Unit,
) {
    Card(modifier = modifier, onClick = { onClick(hobbyClass) }) {
        Text(hobbyClass.name)
    }
}