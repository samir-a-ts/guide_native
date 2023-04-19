package com.ludev.guideproject.features.places_list.presentation.state

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludev.guideproject.features.places_list.domain.Place
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesListSearchViewModel @Inject constructor(
    private val placesListRepository: PlacesListRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    val searchFlow = MutableStateFlow("")

    var job: Job

    init {
        job = viewModelScope.launch {
            searchFlow
                .debounce(1000)
                .onEach { value ->
                    if (value.isNotEmpty()) {
                        requestList(value)
                    }
                }
        }
    }

    val searchInput = mutableStateOf("")

    suspend fun updateInput(input: String) {
        if (input.isEmpty()) return

        if (searchInput.value == input) return

        searchInput.value = input
        searchFlow.emit(input)
    }

    val searchList = mutableStateOf<List<Place>>(listOf())

    val savedSearchList = mutableListOf<List<String>>(listOf())

    private fun requestList(input: String) {

    }

    override fun onCleared() {
        job.cancel()

        super.onCleared()
    }
}