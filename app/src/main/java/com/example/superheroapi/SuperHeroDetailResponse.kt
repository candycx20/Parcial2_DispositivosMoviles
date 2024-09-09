package com.example.superheroapi

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImageResponse,
    @SerializedName("biography") val biography: SuperHeroBiographyResponse? // Biography might be null
)

data class SuperHeroBiographyResponse(
    @SerializedName("full-name") val full_name: String,
    @SerializedName("place-of-birth") val place_of_birth: String,
    @SerializedName("publisher") val publisher: String
)