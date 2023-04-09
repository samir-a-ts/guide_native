package com.ludev.guideproject.features.places_list.presentation.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludev.guideproject.core.presentation.state.EmptyEntityState
import com.ludev.guideproject.core.presentation.state.EntityState
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlacesListUiState(
    val placeListState: EntityState<List<Place>>
)

class PlacesListViewModel : ViewModel() {
    @Inject lateinit var placesListRepository: PlacesListRepository

    private val _uiState = mutableStateOf(
        PlacesListUiState(
            EmptyEntityState()
        )
    )

    val uiState: State<PlacesListUiState> = _uiState

    fun execute() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                placeListState = _uiState.value.placeListState.loading(),
            )

            val result = placesListRepository.listRepos()?.execute() ?: return@launch

                _uiState.value = _uiState.value.copy(
                    placeListState = if (!result.isSuccessful) _uiState.value.placeListState.error(
                        result.message(),
                    ) else  _uiState.value.placeListState.content(
                        result.body() as List<Place>,
                    )
                )
        }
    }
}