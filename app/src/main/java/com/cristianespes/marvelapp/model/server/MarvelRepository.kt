package com.cristianespes.marvelapp.model.server

import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.MarvelApp
import com.cristianespes.marvelapp.model.database.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepository(application: MarvelApp) {

    private val db = application.db

    suspend fun findPopularHeroes(): List<Hero> = withContext(Dispatchers.IO) {

        with(db.marvelDao()) {
            if (heroesCount() <= 0) {

                val heroes = MarvelDb.service.listHeroesAsync(
                    BuildConfig.API_TS,
                    BuildConfig.API_KEY,
                    BuildConfig.API_HASH,
                    80
                ).await()

                insertHeroes(heroes.data?.results?.map(Character::convertToHero) ?: emptyList())
            }

            getAll()
        }
    }

    suspend fun findById(id: Int): Hero = withContext(Dispatchers.IO) {
        db.marvelDao().findById(id)
    }
}

private fun Character.convertToHero() = Hero(
    0,
    name,
    description,
    modified,
    thumbnail = thumbnail?.path.plus(".${thumbnail?.extension}")
)
