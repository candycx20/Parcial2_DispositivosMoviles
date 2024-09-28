package com.example.superheroapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName: String): Response<PokemonDataResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonId: String): Response<PokemonDetailResponse>

}