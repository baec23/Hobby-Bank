package com.baec23.hobbybank.ui.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisplaySection(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier.padding(4.dp),
    headerText: String,
    headerSubtext: String? = null,
    headerIcon: ImageVector? = null,
    dividerColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.Bottom) {
            headerIcon?.let {
                Icon(
                    modifier = Modifier.size(50.dp, 50.dp),
                    imageVector = headerIcon,
                    contentDescription = headerText,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = headerText,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Start,
            )
        }
        headerSubtext?.let{
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = headerSubtext,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Divider(color = dividerColor, thickness = 2.dp)
        Box(modifier = contentModifier)
        {
            content()
        }
    }
}

@Preview
@Composable
fun DisplaySectionPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        DisplaySection(
            modifier = Modifier.fillMaxWidth(),
            headerText = "Hello Section",
            headerSubtext = "Hello subtext this is a description"
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text("Placeholder")
            }
        }
    }
}