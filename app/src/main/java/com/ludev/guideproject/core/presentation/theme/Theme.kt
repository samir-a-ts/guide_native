package com.ludev.guideproject.core.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = PrimaryColor,
    background = Color.White,
)

val Colors.yellowColor: Color
    get() = YellowColor

@Composable
fun GuideProjectTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography.apply {

        },
        shapes = Shapes,
        content = content,
    )
}