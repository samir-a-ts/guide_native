package com.ludev.guideproject.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Gap(
    size: Dp,
) {
    Box(
        modifier = Modifier.size(size)
    )
}