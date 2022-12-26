package com.baec23.hobbybank.ui.comp.togglable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baec23.hobbybank.ui.comp.fadinglazy.FadingLazyColumn
import com.baec23.hobbybank.ui.comp.fadinglazy.FadingLazyHorizontalGrid
import com.baec23.hobbybank.ui.comp.fadinglazy.FadingLazyRow
import com.baec23.hobbybank.ui.comp.fadinglazy.FadingLazyVerticalGrid

@Composable
fun ToggleableIconRow(
    modifier: Modifier = Modifier,
    toggleableIconDataList: List<ToggleableIconData>,
    onItemToggle: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    itemContent: @Composable (ToggleableIconData) -> Unit,
) {
    FadingLazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        items(toggleableIconDataList.count()) {
            Box(modifier = Modifier
                .wrapContentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onItemToggle(it)
                }) {
                itemContent(toggleableIconDataList[it])
            }
        }
    }
}

@Composable
fun ToggleableIconColumn(
    modifier: Modifier = Modifier,
    toggleableIconDataList: List<ToggleableIconData>,
    onItemToggle: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    itemContent: @Composable (ToggleableIconData) -> Unit,
) {

    FadingLazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        items(toggleableIconDataList.count()) {
            Box(modifier = Modifier
                .wrapContentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onItemToggle(it)
                }) {
                itemContent(toggleableIconDataList[it])
            }
        }
    }
}

@Composable
fun ToggleableIconVerticalGrid(
    modifier: Modifier = Modifier,
    columns: GridCells,
    toggleableIconDataList: List<ToggleableIconData>,
    onItemToggle: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable (ToggleableIconData) -> Unit,
) {
    FadingLazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement
    ) {
        items(toggleableIconDataList.count()) {
            Box(modifier = Modifier
                .wrapContentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onItemToggle(it)
                }) {
                itemContent(toggleableIconDataList[it])
            }
        }
    }
}

@Composable
fun ToggleableIconHorizontalGrid(
    modifier: Modifier = Modifier,
    rows: GridCells,
    toggleableIconDataList: List<ToggleableIconData>,
    onItemToggle: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    itemContent: @Composable (ToggleableIconData) -> Unit,
) {
    FadingLazyHorizontalGrid(
        modifier = modifier,
        rows = rows,
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement
    ) {
        items(toggleableIconDataList.count()) {
            Box(modifier = Modifier
                .wrapContentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onItemToggle(it)
                }) {
                itemContent(toggleableIconDataList[it])
            }
        }
    }
}