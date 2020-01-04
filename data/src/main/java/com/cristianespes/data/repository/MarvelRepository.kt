package com.cristianespes.data.repository

import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.domain.Hero

class MarvelRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val tsKey: String,
    private val apiKey: String,
    private val apiHash: String
) {

    suspend fun getHeroes(): List<Hero> {

        if (localDataSource.isEmpty()) {
            for (x in 0 until 1500 step 100) {
                val heroes = remoteDataSource.getHeroes(tsKey, apiKey, apiHash, offset = x)
                localDataSource.saveHeroes(heroes)
            }
        }

        return localDataSource.getHeroes()
    }

    suspend fun findHeroById(id: Int): Hero = localDataSource.findHeroById(id)

    suspend fun update(hero: Hero) = localDataSource.update(hero)

}
