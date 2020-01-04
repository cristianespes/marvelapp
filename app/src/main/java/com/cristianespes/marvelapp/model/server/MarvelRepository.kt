package com.cristianespes.marvelapp.model.server

import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.MarvelApp
import com.cristianespes.marvelapp.model.database.Hero
import com.cristianespes.marvelapp.model.toRoomHero
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

                insertHeroes(heroes.data?.results?.map(Character::toRoomHero) ?: emptyList())
            }

            getAllHeroes()
        }
    }

    suspend fun findById(id: Int): Hero = withContext(Dispatchers.IO) {
        db.marvelDao().findHeroById(id)
    }

    suspend fun update(hero: Hero) = withContext(Dispatchers.IO) {
        db.marvelDao().updateHero(hero)
    }

}
