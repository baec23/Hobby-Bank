package com.baec23.hobbybank.ui.comp

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconTextLabel(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    iconColor: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(imageVector = icon, contentDescription = "$text icon", tint = iconColor)
        Text(
            text = text,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun IconTextLabelPreview() {
    IconTextLabel(
        icon = Icons.Default.LocationOn,
        text = "Incheon"
    )
}