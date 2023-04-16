package com.ludev.guideproject.features.places_list.domain

import retrofit2.Call
import javax.inject.Inject


data class PlacesListPaginationBody(
    val items: Int,
    val page: Int,
)

class PlacesListRepository @Inject constructor(
    private val placesListApi: PlacesListApi
) {
    fun getPlacesList(body: PlacesListPaginationBody): Call<List<Place?>?>? = placesListApi.listRepos(body.items, body.page)
}