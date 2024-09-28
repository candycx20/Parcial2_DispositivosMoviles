package com.example.superheroapi

import com.google.gson.annotations.SerializedName

data class PokemonDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSprites
)

data class PokemonResults (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
)