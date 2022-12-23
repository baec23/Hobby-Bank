package com.baec23.hobbybank.ui.comp.backdrop

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@Composable
fun BlurBackdrop(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .then(if (enabled) modifier.blur(5.dp) else modifier)
    ) {
        content()
    }
}