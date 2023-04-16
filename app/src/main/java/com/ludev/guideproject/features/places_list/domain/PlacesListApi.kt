package com.ludev.guideproject.features.places_list.domain

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesListApi {
    @GET("/place")
    fun listRepos(
        @Query("count") items: Int,
        @Query("offset") page: Int
    ): Call<List<Place?>?>?
}