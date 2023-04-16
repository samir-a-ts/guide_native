package com.ludev.guideproject.features.places_list.domain

import retrofit2.Call
import javax.inject.Inject

class PlacesListRepository @Inject constructor(
    private val placesListApi: PlacesListApi
) {
    fun getPlacesList(): Call<List<Place?>?>? = placesListApi.listRepos()
}