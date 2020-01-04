package com.cristianespes.data.source

import com.cristianespes.domain.Hero

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveHeroes(heroes: List<Hero>)
    suspend fun getHeroes(): List<Hero>
    suspend fun findHeroById(id: Int): Hero
    suspend fun update(hero: Hero)
}