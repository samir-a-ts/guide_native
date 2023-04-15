package com.ludev.guideproject.features.places_list.domain

data class Place(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String,
    val imageUrls: List<String>,
    val placeType: String,
)
