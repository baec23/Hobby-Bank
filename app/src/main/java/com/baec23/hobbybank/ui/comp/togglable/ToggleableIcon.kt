package com.baec23.hobbybank.ui.comp.togglable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ToggleableIcon(
    modifier: Modifier = Modifier,
    toggleableIconData: ToggleableIconData,
    size: Dp = 100.dp,
    toggledOnColor: Color = Color.Black,
    toggledOffColor: Color = Color.LightGray,
    toggledOnSizePercent: Float = 1f,
    toggledOffSizePercent: Float = 0.75f
) {
    val transition = updateTransition(targetState = toggleableIconData.isToggled, label = "toggle")
    val iconColor by transition.animateColor(label = "color") { isToggled ->
        if (isToggled) toggledOnColor else toggledOffColor
    }
    val iconSize by transition.animateFloat(label = "size", transitionSpec = {
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    }) { isToggled ->
        if (isToggled) toggledOnSizePercent else toggledOffSizePercent
    }

    Box(modifier = modifier.size(size)) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(iconSize * 0.95f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                imageVector = toggleableIconData.icon,
                contentDescription = toggleableIconData.name,
                tint = iconColor
            )
            toggleableIconData.label?.let {
                Text(
                    modifier = Modifier.weight(0.5f),
                    text = toggleableIconData.label,
                    color = iconColor,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}
