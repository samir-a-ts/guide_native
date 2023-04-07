package com.ludev.guideproject.core.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.W700,
    ),
    h2 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.8f.sp,
        fontWeight = FontWeight.W700,
    ),
    h3 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    ),
    h4 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
    ),
    subtitle1 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = 1.sp,
    ),
)