package com.ludev.guideproject.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R
import com.ludev.guideproject.core.presentation.theme.disabledColor

@Composable
fun ErrorIndicator(
    title: String,
    description: String,
    iconId: Int = R.drawable.ic_error,
) {
    return Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Error state icon",
            modifier = Modifier.size(64.dp)
        )
        Gap(size = 24.dp)
        Text(
            text = title,
            style = MaterialTheme.typography.h3.copy(
                color = MaterialTheme.colors.disabledColor,
            ),
            textAlign = TextAlign.Center,
        )
        Gap(size = 8.dp)
        Text(
            text = description,
            style = MaterialTheme.typography.h3.copy(
                color = MaterialTheme.colors.disabledColor,
            ),
            textAlign = TextAlign.Center,
        )
    }
}