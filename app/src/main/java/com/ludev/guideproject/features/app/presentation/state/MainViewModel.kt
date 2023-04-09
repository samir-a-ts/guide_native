package com.ludev.guideproject.features.app.presentation.state

import androidx.lifecycle.ViewModel
import com.ludev.guideproject.core.presentation.state.EmptyEntityState
import com.ludev.guideproject.core.presentation.state.EntityState
import com.ludev.guideproject.features.app.domain.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainUiState(
    val placeListState: EntityState<List<Place>>
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MainUiState(
            EmptyEntityState()
        )
    )

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
}