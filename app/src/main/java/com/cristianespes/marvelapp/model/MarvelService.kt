package com.cristianespes.marvelapp.model

import com.cristianespes.marvelapp.model.CharacterDataWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun listHerosAsync(@Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): Deferred<CharacterDataWrapper>
}