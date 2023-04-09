package com.ludev.guideproject.features.places_list.presentation

import Center
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R

@Composable
@Preview
fun PlacesListTabView() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            item {
                PlacesListAppBar()
            }
        }
    }
}

@Composable
fun PlacesListAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Center {
            Text(
                text = stringResource(id = R.string.places_list_app_bar),
                style = MaterialTheme.typography.h3.copy(
                    color = MaterialTheme.colors.onSurface,
                ),
            )
        }
    }
}