package com.cristianespes.marvelapp.model.server

import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.model.server.MarvelDb

class MarvelRepository {

    suspend fun findPopularHeroes() = MarvelDb.service.listHerosAsync(
        BuildConfig.API_TS,
        BuildConfig.API_KEY,
        BuildConfig.API_HASH,
        80
    ).await()

}

