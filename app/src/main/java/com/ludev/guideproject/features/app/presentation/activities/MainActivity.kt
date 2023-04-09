package com.ludev.guideproject.features.app.presentation.activities

import com.ludev.guideproject.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.core.presentation.theme.GuideProjectTheme
import com.ludev.guideproject.features.app.domain.di.DaggerMainComponent
import com.ludev.guideproject.features.app.domain.di.PlacesListModule
import com.ludev.guideproject.features.places_list.presentation.activities.PlacesListTabView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent
            .builder()
            .placesListModule(PlacesListModule())
            .build()
            .initialize(this)

        super.onCreate(savedInstanceState)

        setContent {
            var selectedIndex by remember { mutableStateOf(0) }

            GuideProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomBar(
                            selectedIndex = selectedIndex,
                            onSelected = {
                                selectedIndex = it
                            }
                        )
                    },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = it),
                    ) {
                       when (selectedIndex) {
                           0 -> PlacesListTabView()
                           1 -> Text(text = "1")
                           2 -> Text(text = "2")
                           3 -> Text(text = "3")
                       }
                    }
                }
            }
        }
    }
}

@Composable
fun MainBottomBar(
    selectedIndex: Int? = null,
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
