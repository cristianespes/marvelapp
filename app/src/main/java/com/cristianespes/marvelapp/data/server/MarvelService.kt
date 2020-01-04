package com.cristianespes.marvelapp.data.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun listHeroesAsync(@Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String, @Query("limit") limit: Int =  100, @Query("offset") offset: Int = 0,  @Query("nameStartsWith") nameStartsWith: String? = null): Deferred<CharacterDataWrapper>
}