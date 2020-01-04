package com.cristianespes.data.source

import com.cristianespes.domain.Hero

interface RemoteDataSource {
    suspend fun getHeroes(tsKey: String, apiKey: String, apiHash: String, offset: Int): List<Hero>
}