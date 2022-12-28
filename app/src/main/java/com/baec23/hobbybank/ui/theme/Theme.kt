package com.baec23.hobbybank.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.baec23.hobbybank.R

private val DarkColorScheme = darkColorScheme(
    primary = Yellow,
    secondary = Brown,
    tertiary = Beige
)

private val LightColorScheme = lightColorScheme(
    primary = Yellow,
    secondary = Brown,
    tertiary = Beige,
    primaryContainer = Ivory,
//    background = Ivory

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val GongGothicFontFamily = FontFamily(
    Font(R.font.gong_gothic_bold, weight = FontWeight.Bold),
    Font(R.font.gong_gothic_medium, weight = FontWeight.Medium),
    Font(R.font.gong_gothic_light, weight = FontWeight.Light),
)

@Composable
fun HobbyBankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
    val hobbyBankColorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = hobbyBankColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val hobbyBankTypography = Typography(
        displayLarge = TextStyle(
            lineHeight = 64.sp,
            fontSize = 57.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        displayMedium = TextStyle(
            lineHeight = 52.sp,
            fontSize = 45.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        displaySmall = TextStyle(
            lineHeight = 44.sp,
            fontSize = 36.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),

        headlineLarge = TextStyle(
            lineHeight = 40.sp,
            fontSize = 32.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        headlineMedium = TextStyle(
            lineHeight = 36.sp,
            fontSize = 28.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        headlineSmall = TextStyle(
            lineHeight = 32.sp,
            fontSize = 24.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),

        titleLarge = TextStyle(
            lineHeight = 28.sp,
            fontSize = 22.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        titleMedium = TextStyle(
            lineHeight = 24.sp,
            fontSize = 16.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        titleSmall = TextStyle(
            lineHeight = 20.sp,
            fontSize = 14.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),

        labelLarge = TextStyle(
            lineHeight = 23.sp,
            fontSize = 18.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        labelMedium = TextStyle(
            lineHeight = 20.sp,
            fontSize = 16.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        labelSmall = TextStyle(
            lineHeight = 18.sp,
            fontSize = 14.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),

        bodyLarge = TextStyle(
            lineHeight = 24.sp,
            fontSize = 18.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        bodyMedium = TextStyle(
            lineHeight = 21.sp,
            fontSize = 16.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
        bodySmall = TextStyle(
            lineHeight = 16.sp,
            fontSize = 12.sp,
            fontFamily = GongGothicFontFamily,
            fontWeight = FontWeight.Light
        ),
    )

    val hobbyBankShapes = Shapes(
        extraSmall = RoundedCornerShape(4.dp),
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp),
        extraLarge = RoundedCornerShape(16.dp),
    )

    MaterialTheme(
        colorScheme = hobbyBankColorScheme,
        typography = hobbyBankTypography,
        shapes = hobbyBankShapes,
        content = content,
    )
}