package com.cristianespes.marvelapp.model.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun listHeroesAsync(@Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String, @Query("limit") limit: Int = 20): Deferred<CharacterDataWrapper>
}