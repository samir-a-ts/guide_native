package com.ludev.guideproject.features.places_list.domain

import com.google.gson.annotations.SerializedName

data class Place(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String,
    @SerializedName("urls")
    val imageUrls: List<String>,
    val placeType: String,
)
