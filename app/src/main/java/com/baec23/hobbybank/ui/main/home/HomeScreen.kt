package com.baec23.hobbybank.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.model.HobbyClass

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val allHobbyClasses by viewModel.filteredList.collectAsState()
    val textFieldValue by viewModel.textFieldValue

    Column {
        SearchBar(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            textValue = textFieldValue,
            onValueChanged = { viewModel.onEvent(HomeUiEvent.SearchTextChanged(it)) },
            onSearchCleared = { viewModel.onEvent(HomeUiEvent.SearchTextCleared) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(count = allHobbyClasses.size) { index ->
                HobbyClassItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    hobbyClass = allHobbyClasses[index]!!,
                    onClick = { viewModel.onEvent(HomeUiEvent.HobbyClassClicked(HobbyClass())) }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    textValue: String,
    onValueChanged: (String) -> Unit,
    onSearchCleared: () -> Unit,
) {
    TextField(
        value = textValue,
        onValueChange = onValueChanged,
        modifier = modifier,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyClassItem(
    modifier: Modifier = Modifier,
    hobbyClass: HobbyClass,
    onClick: (HobbyClass) -> Unit,
) {


    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(10.dp)
            .fillMaxWidth(),
        onClick = { onClick(hobbyClass) },
    ) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .padding(15.dp)
                .fillMaxWidth(),
        ) {
//            Image(bitmap = , contentDescription = )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Card(
            modifier = Modifier
                .height(150.dp)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                hobbyClass.name,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                hobbyClass.details,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                hobbyClass.location,
                fontSize = 12.sp
            )
        }

    }
}



//@Composable
//fun ImageCard(
//    painter: Painter?,
//    contentDescriptor: String,
//    hobbyClass: HobbyClass,
//    modifier: Modifier = Modifier,
//    onClick: (HobbyClass) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth(),
//        shape = RoundedCornerShape(15.dp),
//    ) {
//        Box(modifier = Modifier.height(200.dp)) {
//            Image(
//                painter = painter!!,
//                contentDescription = contentDescriptor,
//                contentScale = ContentScale.Crop
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp),
//                contentAlignment = Alignment.BottomStart
//            ) {
//                Text(
//                    hobbyClass.name,
//                    fontSize = 12.sp
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    hobbyClass.details,
//                    fontSize = 12.sp
//                )
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    hobbyClass.location,
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//
//}

