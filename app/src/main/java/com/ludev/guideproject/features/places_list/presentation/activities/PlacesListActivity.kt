package com.ludev.guideproject.features.places_list.presentation.activities

import Center
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R
import com.ludev.guideproject.core.presentation.components.ErrorIndicator
import com.ludev.guideproject.core.presentation.state.ContentEntityState
import com.ludev.guideproject.core.presentation.state.ErrorEntityState
import com.ludev.guideproject.core.presentation.state.LoadingEntityState
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListViewModel


@Composable
fun PlacesListScreen(
    viewModel: PlacesListViewModel,
) {
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
                TappableInput()
            }

            when (val value = viewModel.placesListState.value) {
                is ContentEntityState<List<Place>?> -> {
                    if (value.value == null)
                        item {
                            Center {
                                ErrorIndicator(
                                    title = stringResource(id = R.string.error),
                                    description = stringResource(id = R.string.response_is_empty),
                                    iconId = R.drawable.ic_search,
                                )
                            }
                        }
                            else
                        item {
                            Text(text = "Content")
                        }
                }

                is LoadingEntityState<List<Place>?> -> {
                    item {
                        Text(text = "Loading")
                    }
                }

                is ErrorEntityState<List<Place>?> -> {
                    item {
                        Center {
                            ErrorIndicator(
                                title = stringResource(id = R.string.error),
                                description = stringResource(id = R.string.something_is_odd),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TappableInput() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(
                horizontal = 15.dp,
                vertical = 11.dp,
            )
    ) {
        Row {
           Box(
               modifier = Modifier
               .size(20.dp),
           ) {
               Icon(
                   painter = painterResource(id = R.drawable.ic_search),
                   contentDescription = "Search prefix icon",
               )
           }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                )
            }

            Box(
                modifier = Modifier
                    .size(20.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                )
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