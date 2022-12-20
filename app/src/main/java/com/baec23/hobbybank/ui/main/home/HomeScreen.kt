package com.baec23.hobbybank.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.baec23.hobbybank.model.HobbyClass

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val hobbyClassList by viewModel.filteredHobbyClassList
    val textFieldValue by viewModel.searchFieldText

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            textValue = textFieldValue,
            onValueChanged = { viewModel.onEvent(HomeUiEvent.SearchTextChanged(it)) },
            onSearchCleared = { viewModel.onEvent(HomeUiEvent.SearchTextCleared) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(32.dp)) {
            items(count = hobbyClassList.size) { index ->
                HobbyClassItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    hobbyClass = hobbyClassList[index],
                    onClick = { viewModel.onEvent(HomeUiEvent.HobbyClassClicked(HobbyClass())) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    textValue: String,
    icon: ImageVector = Icons.Default.Search,
    onValueChanged: (String) -> Unit,
    onSearchCleared: () -> Unit,
) {
    TextField(
        modifier = modifier,
        value = textValue,
        onValueChange = onValueChanged,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            IconButton(onClick = onSearchCleared) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "Clear icon"
                )
            }
        }
    )
}

@Composable
fun HobbyClassItem(
    modifier: Modifier = Modifier,
    hobbyClass: HobbyClass,
    onClick: ((HobbyClass) -> Unit)? = null,
    imageCardHeight: Dp = 200.dp,
    textCardBackgroundColor: Color = Color.White
) {
    var mainImageUrl = "https://picsum.photos/1200"
    if (hobbyClass.bitmapUrls.isNotEmpty())
        mainImageUrl = hobbyClass.bitmapUrls.first()

    Column(
        modifier = modifier.clickable { onClick?.let { onClick(hobbyClass) } },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageCardHeight),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                model = mainImageUrl,
                contentScale = ContentScale.FillWidth,
                loading = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.height(100.dp)
                        )
                        Text("Loading...")
                    }
                },
                contentDescription = hobbyClass.name
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = textCardBackgroundColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                Text(text = hobbyClass.name)
                Text(text = hobbyClass.location)
            }
        }
    }
}

@Preview
@Composable
fun HobbyClassItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        HobbyClassItem(
            modifier = Modifier.fillMaxWidth(),
            hobbyClass = HobbyClass(
                name = "Test Name",
                details = "Test details for Test Name class"
            ),
            onClick = {}
        )
    }
}