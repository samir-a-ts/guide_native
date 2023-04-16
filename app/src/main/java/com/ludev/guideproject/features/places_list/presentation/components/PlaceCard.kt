package com.ludev.guideproject.features.places_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.core.presentation.theme.inputColor
import com.ludev.guideproject.features.places_list.domain.Place

@Composable
fun PlaceCard(
    place: Place
) {
    Box(
        modifier = Modifier
            .height(188.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.inputColor),
    ) {

    }
}