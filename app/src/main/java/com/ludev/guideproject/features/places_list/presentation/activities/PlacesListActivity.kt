package com.ludev.guideproject.features.places_list.presentation.activities

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
import com.ludev.guideproject.core.presentation.state.ContentEntityState
import com.ludev.guideproject.core.presentation.state.ErrorEntityState
import com.ludev.guideproject.core.presentation.state.LoadingEntityState
import com.ludev.guideproject.features.app.domain.di.DaggerMainComponent
import com.ludev.guideproject.features.app.domain.di.PlacesListModule
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListViewModel

@Composable
@Preview
fun PlacesListTabView() {
    val viewModel = PlacesListViewModel()

    DaggerMainComponent
        .builder()
        .placesListModule(PlacesListModule())
        .build()
        .initialize(viewModel)

    viewModel.execute()

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

            item {
                when (viewModel.uiState.value.placeListState) {
                    is ContentEntityState -> {
                        Text(text = "Content")
                    }

                    is LoadingEntityState -> {
                        Text(text = "Loading")
                    }

                    is ErrorEntityState -> {
                        Text(text = "Error")
                    }
                }
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