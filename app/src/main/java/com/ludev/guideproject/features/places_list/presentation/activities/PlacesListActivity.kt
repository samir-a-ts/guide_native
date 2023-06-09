package com.ludev.guideproject.features.places_list.presentation.activities

import Center
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ludev.guideproject.R
import com.ludev.guideproject.core.presentation.components.ErrorIndicator
import com.ludev.guideproject.core.presentation.components.Gap
import com.ludev.guideproject.core.presentation.components.LoadingIndicator
import com.ludev.guideproject.core.presentation.state.ContentEntityState
import com.ludev.guideproject.core.presentation.state.ErrorEntityState
import com.ludev.guideproject.core.presentation.state.LoadingEntityState
import com.ludev.guideproject.core.presentation.theme.disabledColor
import com.ludev.guideproject.core.presentation.theme.inputColor
import com.ludev.guideproject.core.presentation.theme.yellowColor
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.presentation.components.PlaceCard
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListViewModel

@Composable
fun PlacesListScreen(
    viewModel: PlacesListViewModel,
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        floatingActionButton = {
            AddNewPlaceFloatingButton()
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { padding ->

        val scrollState = rememberLazyListState()

        LazyColumn(
            state = scrollState,
            modifier = Modifier.padding(padding),
        ) {
            item {
                PlacesListAppBar()
            }

            item {
                TappableInput(
                    onClick = {
                        println("Search")
                        navController.navigate("search")
                    },
                    onFilterClick = {
                        println("Filter")
                    }
                )
            }

            item {
                Gap(size = 38.dp)
            }

            when (val value = viewModel.placesListState.value) {
                is ContentEntityState<List<Place>?> -> {
                    val list = value.value

                    if (list == null)
                        item {
                            Center {
                                ErrorIndicator(
                                    title = stringResource(id = R.string.error),
                                    description = stringResource(id = R.string.response_is_empty),
                                    iconId = R.drawable.ic_search,
                                )
                            }
                        }
                            else {
                        items(list, key = {
                            item: Place ->  item.id
                        }) { item ->

                            val place = item as? Place

                            if (place != null) {
                                PlaceCard(
                                    place = place,
                                )
                                Gap(size = 24.dp)
                            }
                        }

                        val reachedEnd = !scrollState.canScrollForward && scrollState.canScrollBackward

                        if ((reachedEnd && list.isNotEmpty()) || list.isEmpty()) {
                            viewModel.reload()
                        }
                    }
                }

                is LoadingEntityState<List<Place>?> -> {
                    val list = value.value

                    if (list == null || list.isEmpty())
                        item {
                            Center {
                                LoadingIndicator()
                            }
                        }
                    else
                            items(list) { item ->

                                val place = item as? Place

                                if (place != null) {
                                    PlaceCard(
                                        place = place,
                                    )
                                    Gap(size = 24.dp)
                                }
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
fun TappableInput(
    onClick: () -> Unit,
    onFilterClick: () -> Unit,
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(
                RoundedCornerShape(12.dp),
            )
            .background(
                color = MaterialTheme.colors.inputColor,
            ).clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 11.dp,
                )
        ) {
            Gap(size = 15.dp)
           Icon(
               modifier = Modifier
                   .size(20.dp),
               painter = painterResource(id = R.drawable.ic_search),
               contentDescription = "Search prefix icon",
           )
            Gap(size = 15.dp)
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.h4.copy(
                    color =  MaterialTheme.colors.disabledColor,
                    lineHeight = 14.sp,
                ),
            )
            Box(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                       onFilterClick()
                    },
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter suffix icon",
            )
            Gap(size = 15.dp)
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

@Composable
fun AddNewPlaceFloatingButton() {
    Box(
        modifier = Modifier
            .size(177.dp, 46.dp)
            .clip(RoundedCornerShape(23.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colors.yellowColor,
                        MaterialTheme.colors.primary,
                    ),
                ),
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "New place add icon",
                tint = MaterialTheme.colors.background,
            )
            Gap(size = 14.dp)
            Text(
                text = stringResource(id = R.string.create_new_place),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.background,
                    fontWeight = FontWeight.W700,
                ),
            )
        }
    }
}