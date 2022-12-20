package com.baec23.hobbybank.ui.main.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.ui.comp.IconTextLabel
import kotlinx.coroutines.delay

private const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val hobbyClassList by viewModel.filteredHobbyClassList
    val textFieldValue by viewModel.searchFieldText

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            textValue = textFieldValue,
            onValueChanged = { viewModel.onEvent(HomeUiEvent.SearchTextChanged(it)) },
            onSearchCleared = { viewModel.onEvent(HomeUiEvent.SearchTextCleared) }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(count = hobbyClassList.size) { index ->
                HobbyClassItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    hobbyClass = hobbyClassList[index],
                    nextImageDelayMillis = 5000,
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
    imageHeight: Dp = 200.dp,
    cardBackgroundColor: Color = Color.White,
    nextImageDelayMillis: Long = 0  //0 = no automatic switching
) {
    val imageUrls = hobbyClass.bitmapUrls
    var currImageIndex by remember { mutableStateOf(0) }
    var currImageUrl by remember { mutableStateOf(if (imageUrls.isNotEmpty()) imageUrls.first() else "https://picsum.photos/1200") }
    var isSwitching by remember { mutableStateOf(false) }

    suspend fun changeImage() {
        Log.d(TAG, "changeImage: Called")
        delay(nextImageDelayMillis)
        isSwitching = true
        delay(200)
        isSwitching = false
        currImageIndex = if (currImageIndex == imageUrls.size - 1) 0 else currImageIndex + 1
        currImageUrl = imageUrls[currImageIndex]
    }

    if (nextImageDelayMillis > 0) {
        LaunchedEffect(currImageUrl) {
            changeImage()
        }
    }

    Column(
        modifier = modifier.clickable { onClick?.let { onClick(hobbyClass) } },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            val animatedColor by animateColorAsState(
                targetValue = if (isSwitching) Color.LightGray else Color.Black,
                animationSpec = tween(durationMillis = 200)
            )
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight),
                model = currImageUrl,
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                contentDescription = hobbyClass.name,
                colorFilter = ColorFilter.tint(animatedColor, blendMode = BlendMode.Screen)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.weight(0.8f),
                        text = hobbyClass.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconTextLabel(
                        modifier = Modifier.weight(0.2f),
                        icon = Icons.Default.LocationOn,
                        text = hobbyClass.location
                    )
                }
            }
        }
    }
}

@Composable
fun HobbyClassItem2(
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
                Text(
                    text = hobbyClass.location,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
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
                name = "Test Name REally Long name the quick brown fox jumps",
                details = "Test details for Test Name class",
                location = "Guro-gu, Seoul"
            ),
            onClick = {}
        )
    }
}