package com.example.superheroapi

import com.google.gson.annotations.SerializedName

data class SuperheroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroResults>
)

data class SuperHeroResults (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImageResponse
)

data class SuperheroImageResponse(
    @SerializedName("url") val url: String
)