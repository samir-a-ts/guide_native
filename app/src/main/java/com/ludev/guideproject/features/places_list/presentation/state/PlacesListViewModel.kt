package com.ludev.guideproject.features.places_list.presentation.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludev.guideproject.core.presentation.state.ContentEntityState
import com.ludev.guideproject.core.presentation.state.EntityState
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.domain.PlacesListPaginationBody
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlacesListViewModel @Inject constructor(
    private val placesListRepository: PlacesListRepository,
) : ViewModel() {

    private val _placesListState = mutableStateOf<EntityState<List<Place>?>>(
        ContentEntityState(listOf())
    )

    val placesListState: State<EntityState<List<Place>?>> = _placesListState

    fun updateState(generator: (current: EntityState<List<Place>?>) -> EntityState<List<Place>?>) {
        _placesListState.value = generator(_placesListState.value)
    }

    private var currentPage: Int = 0

    fun initialize() {
        viewModelScope.launch {
            updateState {
                it.loading()
            }

            loadData()
        }
    }

    fun reload() {
        viewModelScope.launch {
            loadData()
        }
    }

    private fun loadData() {
        val result = placesListRepository.getPlacesList(
            PlacesListPaginationBody(
                page = currentPage,
                items = 10,
            )
        )

        result?.enqueue(
            object : Callback<List<Place?>?> {
                override fun onResponse(
                    call: retrofit2.Call<List<Place?>?>,
                    response: Response<List<Place?>?>
                ) {
                    if (!response.isSuccessful) {
                        updateState {
                            it.error(response.message())
                        }

                        return
                    }

                    updateState {
                        it.content(it.value?.plus((response.body() as List<Place>?)) as List<Place>?)
                    }

                    currentPage++
                }

                override fun onFailure(call: retrofit2.Call<List<Place?>?>, t: Throwable) {
                    updateState {
                        it.error(t.message)
                    }
                }

            }
        )
    }
}