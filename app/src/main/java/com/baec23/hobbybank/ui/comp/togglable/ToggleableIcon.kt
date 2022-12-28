package com.baec23.hobbybank.ui.comp.togglable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

//
//@Composable
//fun ToggleableIcon(
//    modifier: Modifier = Modifier,
//    toggleableIconData: ToggleableIconData,
//    size: Dp = 100.dp,
//    toggledOnColor: Color = Color.Black,
//    toggledOffColor: Color = Color.LightGray,
//    toggledOnSizePercent: Float = 1f,
//    toggledOffSizePercent: Float = 0.75f
//) {
//    val transition = updateTransition(targetState = toggleableIconData.isToggled, label = "toggle")
//    val iconColor by transition.animateColor(label = "color") { isToggled ->
//        if (isToggled) toggledOnColor else toggledOffColor
//    }
//    val iconSize by transition.animateFloat(label = "size", transitionSpec = {
//        spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessMedium
//        )
//    }) { isToggled ->
//        if (isToggled) toggledOnSizePercent else toggledOffSizePercent
//    }
//
//    Box(modifier = modifier.size(size)) {
//        Column(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .fillMaxSize(iconSize * 0.95f),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxSize(),
//                imageVector = toggleableIconData.icon,
//                contentDescription = toggleableIconData.name,
//                tint = iconColor
//            )
//            toggleableIconData.label?.let {
//                Text(
//                    modifier = Modifier.weight(0.5f),
//                    text = toggleableIconData.label,
//                    color = iconColor,
//                    overflow = TextOverflow.Clip
//                )
//            }
//        }
//    }
//}

@Composable
fun Toggleable(
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onToggle: () -> Unit,
    toggledOnColor: Color = MaterialTheme.colorScheme.primary,
    toggledOffColor: Color = Color.LightGray,
    blendMode: BlendMode = BlendMode.Screen,
    toggleOnScale: Float = 1f,
    toggledOffScale: Float = 0.75f,
    content: @Composable BoxScope.() -> Unit
) {
    val isToggledTransition =
        updateTransition(targetState = isToggled, label = "isToggledTransition")
    val animatedColor by isToggledTransition.animateColor(label = "animatedColor") { if (it) toggledOnColor else toggledOffColor }
    val animatedScale by isToggledTransition.animateFloat(
        label = "animatedScale",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        }) { if (it) toggleOnScale else toggledOffScale }

    Box(modifier = modifier
        .scale(animatedScale)
        .drawWithContent {
            drawContent()
            drawRect(size = size, color = animatedColor, blendMode = blendMode)
        }
        .clickable(interactionSource = MutableInteractionSource(), indication = null) {
            onToggle()
        }
    ) {
        content()
    }
}

@Composable
fun ToggleableIcon(
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    imageVector: ImageVector,
    size: DpSize = DpSize(100.dp, 100.dp),
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    contentDescription: String? = null,
    label: String? = null,
    toggledOnColor: Color = MaterialTheme.colorScheme.primary,
    toggledOffColor: Color = Color.LightGray,
    toggledOnScale: Float = 1f,
    toggledOffScale: Float = 0.75f,
    onToggle: () -> Unit,
) {
    val isToggledTransition = updateTransition(targetState = isToggled, label = "isToggled")
    val animatedColor by isToggledTransition.animateColor(label = "animatedColor") { if (it) toggledOnColor else toggledOffColor }
    val animatedSize by isToggledTransition.animateFloat(label = "animatedSize", transitionSpec = {
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    }) { if (it) toggledOnScale else toggledOffScale }

    Box(
        modifier = modifier
            .size(size)
            .clickable(interactionSource = interactionSource, indication = null) { onToggle() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(animatedSize * 0.95f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = animatedColor
            )
            label?.let {
                Text(
                    modifier = Modifier.weight(0.5f),
                    text = label,
                    color = animatedColor,
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    minLines = 1
                )
            }
        }
    }
}
