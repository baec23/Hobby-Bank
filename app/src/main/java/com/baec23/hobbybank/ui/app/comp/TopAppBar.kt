package com.baec23.hobbybank.ui.app.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    screenName: String,
    onLogout: () -> Unit = {}
) {
    var dropdownMenuExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = screenName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        IconButton(onClick = {
            dropdownMenuExpanded = true
        }) {
            DropdownMenu(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                expanded = dropdownMenuExpanded,
                onDismissRequest = { dropdownMenuExpanded = false }) {
                DropdownMenuContent(modifier = Modifier.height(25.dp)) {
                    dropdownMenuExpanded = false
                    onLogout()
                }
            }
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
        }
    }
}

@Composable
fun DropdownMenuContent(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    DropdownMenuItem(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        colors = MenuDefaults.itemColors(
            textColor = MaterialTheme.colorScheme.onPrimary,
            leadingIconColor = MaterialTheme.colorScheme.onPrimary
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout")
        },
        text = {
            Text(text = "로그아웃",
            style = MaterialTheme.typography.titleSmall)
        }, onClick = onLogout
    )
}