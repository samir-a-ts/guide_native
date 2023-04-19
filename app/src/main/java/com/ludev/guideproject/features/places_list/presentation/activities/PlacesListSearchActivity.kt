package com.ludev.guideproject.features.places_list.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ludev.guideproject.R
import com.ludev.guideproject.core.presentation.components.Gap
import com.ludev.guideproject.core.presentation.theme.disabledColor
import com.ludev.guideproject.core.presentation.theme.inputColor
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListSearchViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun PlacesListSearchActivity(
    viewModel: PlacesListSearchViewModel,
) {
    val coroutineScope = rememberCoroutineScope()

    val value = viewModel.searchInput.value

    LazyColumn(
        modifier = Modifier.padding(
            horizontal = 16.dp,
        )
    ) {
        item {
            PlacesListAppBar()
        }

        item {
            PlacesSearchInput(
                value = value,
                onValueChange = {newValue ->
                    coroutineScope.launch {
                        viewModel.updateInput(newValue)
                    }
                },
                onInputClear = {
                    coroutineScope.launch {
                        viewModel.updateInput("")
                    }
                },
            )
        }

        item {
            Gap(size = 38.dp)
        }
    }
}

@Composable
fun PlacesSearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    onInputClear: () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.inputColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = MaterialTheme.typography.h4.copy(
            MaterialTheme.colors.disabledColor,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search icon",
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onInputClear()
                    },
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear input button icon",
            )
        },
        placeholder = {
            Text(
                modifier = Modifier.fillMaxHeight(),
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.disabledColor
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(0.dp)
            .background(
                color = MaterialTheme.colors.inputColor,
            ),
    )
}