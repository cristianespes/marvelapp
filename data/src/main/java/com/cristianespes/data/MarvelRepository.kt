package com.cristianespes.data

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
            val heroes = remoteDataSource.getHeroes(tsKey, apiKey, apiHash)
            localDataSource.saveHeroes(heroes)
        }

        return localDataSource.getHeroes()
    }

    suspend fun findHeroById(id: Int): Hero = localDataSource.findHeroById(id)

    suspend fun update(hero: Hero) = localDataSource.update(hero)

}

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveHeroes(heroes: List<Hero>)
    suspend fun getHeroes(): List<Hero>
    suspend fun findHeroById(id: Int): Hero
    suspend fun update(hero: Hero)
}

interface RemoteDataSource {
    suspend fun getHeroes(tsKey: String, apiKey: String, apiHash: String): List<Hero>
}