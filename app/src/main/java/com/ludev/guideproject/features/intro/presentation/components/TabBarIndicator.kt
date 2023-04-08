package com.ludev.guideproject.features.intro.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.core.presentation.theme.disabledColor

@Composable
fun TabBarIndicator(
    current: Int,
    length: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        ) {
            for (i in 0 until length) {
                Indicator(current == i)
            }
        }
}

@Composable
fun Indicator(selected: Boolean) {
    Box(
        modifier =
            Modifier
                .padding(horizontal = 4.dp)
                .size(if (selected) 24.dp else 8.dp, 8.dp)
                .clip(CircleShape)
                .background(
                    color = if (selected)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.disabledColor
                )
    )
}