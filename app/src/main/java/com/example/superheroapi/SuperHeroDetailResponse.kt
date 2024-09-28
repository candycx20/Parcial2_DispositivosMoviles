package com.example.superheroapi

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSpritesDetail,
    @SerializedName("abilities") val abilities: List<PokemonAbility>
)

data class PokemonSpritesDetail(
    @SerializedName("front_default") val frontDefault: String
)

data class PokemonAbility(
    @SerializedName("ability") val ability: AbilityDetails
)

data class AbilityDetails(
    @SerializedName("name") val name: String
)