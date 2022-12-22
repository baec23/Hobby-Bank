package com.baec23.hobbybank.ui.comp.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HBButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    text: String,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
    ){
        Text(text)
    }
}