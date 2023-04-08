package com.ludev.guideproject.core.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = PrimaryColor,
    onPrimary = Color.White,
    onSurface = MainColor,
    onSecondary = SecondaryColor,
    background = Color.White,
)

val Colors.yellowColor: Color
    get() = YellowColor


val Colors.disabledColor: Color
    get() = DisabledColor

val Colors.thirdColor: Color
    get() = ThirdColor

@Composable
fun GuideProjectTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}