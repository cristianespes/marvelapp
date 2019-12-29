package com.cristianespes.marvelapp.model

import com.cristianespes.marvelapp.BuildConfig

class MarvelRepository {

    suspend fun findPopularHeroes() = MarvelDb.service.listHerosAsync(
        BuildConfig.API_TS,
        BuildConfig.API_KEY,
        BuildConfig.API_HASH,
        80
    ).await()

}

