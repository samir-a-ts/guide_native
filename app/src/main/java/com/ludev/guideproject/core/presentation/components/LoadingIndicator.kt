package com.ludev.guideproject.core.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R

@Composable
fun LoadingIndicator() {
    var rotationAngle by remember { mutableStateOf(0f) }

    val rotationAnimation: Float by animateFloatAsState(
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Restart)
    )

    Image(
        painter = painterResource(id = R.drawable.ic_loader),
        contentDescription = "Loading indicator",
        modifier = Modifier
            .size(144.dp)
            .rotate(rotationAngle)
    )

    LaunchedEffect(rotationAnimation) {
        rotationAngle = rotationAnimation
    }
}