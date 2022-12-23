package com.baec23.hobbybank.ui.comp.button

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.awaitCancellation

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

    if(isPressed){
        Log.d(TAG, "HBButton2: pressed")
        DisposableEffect(Unit){
            onDispose{
                Log.d(TAG, "HBButton2: released")
            }
        }
    }

    var currentState by remember { mutableStateOf(ButtonState.NotPressed) }

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
            .clickable(interactionSource = interactionSource, indication = null){

            },
        color = Color.Red
    ) {
        Text(text)
    }
}


private enum class ButtonState {
    NotPressed,
    Pressed,
    Released,
    Clicked,
}