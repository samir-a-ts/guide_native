package com.ludev.guideproject.features.places_list.presentation.state

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

    val placesListState = mutableStateOf<EntityState<List<Place>?>>(
        ContentEntityState(listOf())
    )

    fun updateState(generator: (current: EntityState<List<Place>?>) -> EntityState<List<Place>?>) {
        placesListState.value = generator(placesListState.value)
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

                    val content = arrayListOf<Place>()

                    placesListState.value.value?.let { content.addAll(it) }

                    val result = response.body() as Collection<Place>

                    content.addAll(result)

                    updateState {
                        it.content(content.toList())
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