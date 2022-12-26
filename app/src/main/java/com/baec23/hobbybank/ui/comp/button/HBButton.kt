@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.baec23.hobbybank.ui.comp.button

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baec23.hobbybank.ui.comp.loading.LoadingDotsIndicator
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    ) {
        Text(text)
    }
}

private const val TAG = "HBButton"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HBButton3(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Idle,
    width: Dp = 150.dp,
    height: Dp = 50.dp,
    onClick: () -> Unit,
) {
    var buttonAnimationState by remember { mutableStateOf(ButtonAnimationState.Idle) }

    val coroutineScope = rememberCoroutineScope()

    val borderSize by animateDpAsState(
        targetValue = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> 1.dp
            ButtonAnimationState.Pressed -> 4.dp
        },
        animationSpec = when (buttonAnimationState) {
            ButtonAnimationState.Idle -> spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
            ButtonAnimationState.Pressed -> tween(10)
        },
        finishedListener = {
            if(buttonAnimationState == ButtonAnimationState.Pressed){
                buttonAnimationState = ButtonAnimationState.Idle
            }
        }
    )

    val contentScale by animateFloatAsState(
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
            if(buttonAnimationState == ButtonAnimationState.Pressed){
                buttonAnimationState = ButtonAnimationState.Idle
            }
        }
    )

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .animateContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .size(
                    width = width,
                    height = height
                )
                .border(
                    width = borderSize,
                    shape = RoundedCornerShape(height / 2),
                    color = Color.Black
                )
//                .padding(8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    buttonAnimationState = ButtonAnimationState.Pressed
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                modifier = Modifier.scale(contentScale),
                targetState = state,
                transitionSpec = {
                    scaleIn() with scaleOut()
                }
            ) {
                when (it) {
                    ButtonState.Idle -> Text(
                        text = text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    ButtonState.Loading -> LoadingDotsIndicator(
                        modifier = Modifier.width(
                            minOf(
                                width * 0.5f,
                                100.dp
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HBButton3Preview() {
    HBButton3(text = "Hello there", onClick = {})
}

@Composable
fun HBButton2(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    text: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    if (isPressed) {
        Log.d(TAG, "HBButton2: pressed")
        DisposableEffect(Unit) {
            onDispose {
                Log.d(TAG, "HBButton2: released")
            }
        }
    }

//    var currentState by remember { mutableStateOf(ButtonState.NotPressed) }

//    LaunchedEffect(isPressed) {
//        if (currentState == ButtonState.Pressed) {
//            if (!isPressed)
//                currentState = ButtonState.Released
//        } else {
//            if (isPressed)
//                currentState = ButtonState.Pressed
//        }
//        Log.d("AHH", "HBButton2: isPressed : $isPressed")
//    }

//    LaunchedEffect(currentState) {
//        when (currentState) {
//            ButtonState.NotPressed -> {}
//            ButtonState.Pressed -> {}
//            ButtonState.Released -> {}
//            ButtonState.Clicked -> currentState = ButtonState.Pressed
//        }
//    }
    Surface(
        modifier = modifier
            .size(200.dp)
            .clickable(interactionSource = interactionSource, indication = null) {

            },
        color = Color.Red
    ) {
        Text(text)
    }
}

private enum class ButtonAnimationState {
    Idle,
    Pressed,
}

enum class ButtonState {
    Idle,
    Loading,
}