package com.baec23.hobbybank.ui.comp.button

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.baec23.hobbybank.ui.comp.loading.LoadingDotsIndicator

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StatefulButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    state: ButtonState = ButtonState.Enabled,
    shape: Shape = CircleShape,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    disabledBorderColor: Color = Color.LightGray,
    borderWidth: Dp = 1.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loadingContent: @Composable () -> Unit = { LoadingDotsIndicator() },
    content: @Composable () -> Unit,
) {
    //region Animation
    var buttonAnimationState by remember { mutableStateOf(ButtonAnimationState.Idle) }

    val animatedBorderWidth by animateDpAsState(
        targetValue = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> borderWidth
            ButtonAnimationState.Pressed -> borderWidth * 4
        },
        animationSpec = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )

            ButtonAnimationState.Pressed -> tween(10)
        },
        finishedListener = {
            if (buttonAnimationState == ButtonAnimationState.Pressed) {
                buttonAnimationState = ButtonAnimationState.Idle
            }
        }
    )

    val animatedContentScale by animateFloatAsState(
        targetValue = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> 1f
            ButtonAnimationState.Pressed -> 0.95f
        },
        animationSpec = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )

            ButtonAnimationState.Pressed -> tween(10)
        },
        finishedListener = {
            if (buttonAnimationState == ButtonAnimationState.Pressed) {
                buttonAnimationState = ButtonAnimationState.Idle
            }
        }
    )
    //endregion
    Box(
        modifier = modifier
            .border(
                width = animatedBorderWidth,
                shape = shape,
                color = if (state == ButtonState.Disabled) disabledBorderColor else borderColor
            )
            .run {
                when (state) {
                    ButtonState.Enabled -> this.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        buttonAnimationState = ButtonAnimationState.Pressed
                        onClick()
                    }

                    ButtonState.Disabled, ButtonState.Loading -> this
                }
            }
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        var mySize by remember { mutableStateOf(Size.Zero) }
        AnimatedContent(
            modifier = Modifier
                .scale(animatedContentScale)
                .graphicsLayer { alpha = 0.99f }
                .run {
                    if (state == ButtonState.Disabled) {
                        this.drawWithContent {
                            drawContent()
                            drawRect(
                                size = size,
                                color = Color(0x64000000),
                                blendMode = BlendMode.DstIn
                            )
                        }
                    } else this
                }
                .onGloballyPositioned { layoutCoordinates ->
                    Log.d(
                        "Hello",
                        "StatefulButton: AnimatedContent onGloballyPositioned : ${layoutCoordinates.size}"
                    )
                    mySize = layoutCoordinates.size.toSize()
                },
            targetState = state,
            transitionSpec = {
                scaleIn() with scaleOut()
            }
        ) {
            when (it) {
                ButtonState.Enabled, ButtonState.Disabled -> content()
                ButtonState.Loading -> {
                    val myDpSize = with(LocalDensity.current) { mySize.toDpSize() }
                    Box(
                        modifier = Modifier.size(myDpSize),
                        contentAlignment = Alignment.Center
                    ) {
                        loadingContent()
                    }
                }
            }
        }
    }
}

@Composable
fun StatefulButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.primary,
    state: ButtonState = ButtonState.Enabled,
    shape: Shape = CircleShape,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    disabledBorderColor: Color = Color.LightGray,
    borderWidth: Dp = 1.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loadingContent: @Composable () -> Unit = { LoadingDotsIndicator() },
    onClick: () -> Unit,
) {
    StatefulButton(
        modifier = modifier,
        state = state,
        shape = shape,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor,
        borderWidth = borderWidth,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = {
            Text(
                text = text,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        loadingContent = loadingContent,
        onClick = onClick
    )
}

private enum class ButtonAnimationState {
    Idle,
    Pressed,
}

enum class ButtonState {
    Enabled,
    Disabled,
    Loading,
}