package com.baec23.hobbybank.ui.comp

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageAdder(
    modifier: Modifier = Modifier,
    cardWidth: Dp = 100.dp,
    addedImages: List<Bitmap>,
    onImageAdded: (Bitmap) -> Unit,
    onImageRemoved: (Bitmap) -> Unit,
    addButtonIconColor: Color = MaterialTheme.colorScheme.primary,
    addButtonBackgroundColor: Color = MaterialTheme.colorScheme.tertiary
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(imageUri) {
        imageUri?.let {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
            bitmap.value?.let { bitmap ->
                onImageAdded(bitmap)
            }
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        AddImageButton(
            modifier = Modifier
                .width(cardWidth)
                .aspectRatio(1f),
            iconColor = addButtonIconColor,
            backgroundColor = addButtonBackgroundColor
        ) {
            launcher.launch("image/*")
        }
        ImageCardRow(
            bitmaps = addedImages,
            cardWidth = cardWidth,
            onRemove = { onImageRemoved(it) }
        )
    }
}


@Composable
fun ImageCardRow(
    bitmaps: List<Bitmap>,
    cardWidth: Dp,
    onRemove: (Bitmap) -> Unit = {},
) {
    LazyRow(content = {
        items(count = bitmaps.size) {
            AddedImage(
                modifier = Modifier
                    .width(cardWidth)
                    .aspectRatio(1f),
                bitmap = bitmaps[it],
                onRemove = onRemove
            )
        }
    })
}

@Composable
fun AddImageButton(
    modifier: Modifier = Modifier,
    iconColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    ImageCard(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Add Image",
                tint = iconColor
            )
        }
    }
}

@Composable
fun AddedImage(
    modifier: Modifier = Modifier,
    onRemove: (Bitmap) -> Unit = {},
    bitmap: Bitmap,
) {
    var expanded by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(bitmap)

    ImageCard(
        modifier = modifier,
        onClick = { expanded = true }
    ) {
        DropdownMenu(
            modifier = Modifier.padding(0.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text("Remove") },
                onClick = {
                    onRemove(bitmap)
                    expanded = false
                })
        }
        Image(
            painter = painter,
            contentDescription = "Added Image",
            contentScale = ContentScale.FillWidth
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = onClick
    ) {
        content()
    }
}