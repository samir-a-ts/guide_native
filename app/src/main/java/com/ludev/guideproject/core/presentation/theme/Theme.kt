package com.ludev.guideproject.core.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ludev.guideproject.ui.theme.PrimaryColor
import com.ludev.guideproject.ui.theme.Shapes
import com.ludev.guideproject.ui.theme.Typography
import com.ludev.guideproject.ui.theme.YellowColor

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
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}