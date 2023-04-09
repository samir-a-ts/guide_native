package com.ludev.guideproject.features.places_list.domain

import retrofit2.Call
import retrofit2.http.GET

interface PlacesListRepository {
    @GET("/place")
    fun listRepos(): Call<List<Place?>?>?
}