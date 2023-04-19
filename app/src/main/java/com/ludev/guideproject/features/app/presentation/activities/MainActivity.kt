package com.ludev.guideproject.features.app.presentation.activities

import com.ludev.guideproject.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ludev.guideproject.core.presentation.theme.GuideProjectTheme
import com.ludev.guideproject.features.places_list.presentation.activities.PlacesListScreen
import com.ludev.guideproject.features.places_list.presentation.activities.PlacesListSearchActivity
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListSearchViewModel
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val placesListViewModel: PlacesListViewModel by viewModels()

    private val placesListSearchViewModel: PlacesListSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var selectedIndex by remember { mutableStateOf(0) }

            val rootNavController = rememberNavController()

            GuideProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomBar(
                            selectedIndex = selectedIndex,
                            onSelected = {
                                selectedIndex = it
                            },
                            navController = rootNavController,
                        )
                    },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = it),
                    ) {
                        NavHost(
                            navController = rootNavController,
                            startDestination = "list",

                        ) {
                            listGraph(rootNavController)

                            composable("map") {
                                Text(text = "Map")
                            }

                            composable("favorite") {
                                Text(text = "Favorite")
                            }

                            composable("settings") {
                                Text(text = "Settings")
                            }
                        }
                    }
                }
            }
        }
    }


    private fun NavGraphBuilder.listGraph(navController: NavController) {
        navigation(startDestination = "root", route = "list") {
            composable("root") {
                PlacesListScreen(
                    viewModel = placesListViewModel,
                    navController = navController,
                )
            }
            composable("search") {
                PlacesListSearchActivity(
                    viewModel = placesListSearchViewModel,
                )
            }
        }
    }
}

@Composable
fun MainBottomBar(
    selectedIndex: Int? = null,
    navController: NavController,
    onSelected: (Int) -> Unit = {},
) {
    val icons = listOf(
        R.drawable.list,
        R.drawable.map,
        R.drawable.heart,
        R.drawable.settings,
    )

    val filledIcons = listOf(
        R.drawable.list_filled,
        R.drawable.map_filled,
        R.drawable.heart_filled,
        R.drawable.settings_filled,
    )

    val routes = listOf(
        "list",
        "map",
        "favorite",
        "settings",
    )

    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
    ) {
        for (i in 0 until 4) {

            val selected = selectedIndex == i

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onSelected(i)

                    navController.navigate(routes[i])
                },
                selectedContentColor = MaterialTheme.colors.onSurface,
                unselectedContentColor = MaterialTheme.colors.onSecondary,
                icon = {
                    Icon(
                        painter = painterResource(
                            if (selected) filledIcons[i] else icons[i]
                        ),
                        contentDescription = "Bottom navigation bar icon"
                    )
                },
                enabled = true,
            )
        }
    }
}
