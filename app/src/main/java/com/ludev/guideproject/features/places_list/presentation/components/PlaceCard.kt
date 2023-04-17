package com.ludev.guideproject.features.places_list.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.ludev.guideproject.core.presentation.components.Gap
import com.ludev.guideproject.core.presentation.theme.disabledColor
import com.ludev.guideproject.core.presentation.theme.inputColor
import com.ludev.guideproject.features.places_list.domain.Place

@Composable
fun PlaceCard(
    place: Place
) {
    val painter = rememberImagePainter(
        data = place.imageUrls.first(),
        builder = {
            scale(Scale.FILL)
        }
    )

    Box(
        modifier = Modifier
            .height(188.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.inputColor),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Box(
                modifier = Modifier.weight(1f),
                content = {
                    Image(
                        painter = painter,
                        contentDescription = "Place image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                ) {
                    Gap(size = 16.dp)
                    Text(
                        text = place.name,
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSecondary,
                        )
                    )
                    Gap(size = 16.dp)
                    Text(
                        text = place.description,
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.disabledColor,
                        ),
                        maxLines = 1
                    )
                }
        }
    }
}