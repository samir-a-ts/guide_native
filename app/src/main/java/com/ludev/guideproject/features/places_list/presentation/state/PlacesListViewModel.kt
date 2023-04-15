package com.ludev.guideproject.features.places_list.presentation.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludev.guideproject.core.presentation.state.ContentEntityState
import com.ludev.guideproject.core.presentation.state.EntityState
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

data class PlacesListUiState(
    val placeListState: EntityState<List<Place>>
)

class PlacesListViewModel : ViewModel() {
    @Inject lateinit var placesListRepository: PlacesListRepository

    private val _uiState = mutableStateOf(
        PlacesListUiState(
            ContentEntityState(null)
        )
    )

    val uiState: State<PlacesListUiState> = _uiState

    fun execute() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                placeListState = _uiState.value.placeListState.loading(),
            )

            val result = placesListRepository.listRepos()

            result?.enqueue(
                object : Callback<List<Place?>?> {
                    override fun onResponse(
                        call: retrofit2.Call<List<Place?>?>,
                        response: Response<List<Place?>?>
                    ) {
                        if (!response.isSuccessful) {
                            _uiState.value = _uiState.value.copy(
                                placeListState =  _uiState.value.placeListState.error(
                                    response.message(),
                                )
                            )

                            return
                        }

                        val result = response.body()

                        _uiState.value = _uiState.value.copy(
                            placeListState = _uiState.value.placeListState.content(result as List<Place>?),
                        )
                    }

                    override fun onFailure(call: retrofit2.Call<List<Place?>?>, t: Throwable) {
                        _uiState.value = _uiState.value.copy(
                            placeListState = _uiState.value.placeListState.error(t.message)
                        )
                    }

                }
            )

//            val result = placesListRepository.listRepos()?.execute() ?: return@launch
//

        }
    }
}