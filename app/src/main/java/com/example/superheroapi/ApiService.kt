package com.example.superheroapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/65f2dadd18b6207772b2c89aae849222/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperheroDataResponse>

    @GET("api/65f2dadd18b6207772b2c89aae849222/{id}")
    suspend fun getSuperheroeDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>

}