package com.cristianespes.marvelapp.data.database

import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.marvelapp.data.toDomainHero
import com.cristianespes.marvelapp.data.toRoomHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.cristianespes.domain.Hero as DomainHero

class RoomDataSource(db: MarvelDatabase) :
    LocalDataSource {

    private val marvelDao = db.marvelDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        marvelDao.heroesCount() <= 0
    }

    override suspend fun saveHeroes(heroes: List<DomainHero>) = withContext(Dispatchers.IO) {
        marvelDao.insertHeroes(heroes.map(DomainHero::toRoomHero))
    }

    override suspend fun getHeroes(): List<DomainHero> = withContext(Dispatchers.IO) {
        marvelDao.getAllHeroes().map { it.toDomainHero() }
    }

    override suspend fun findHeroById(id: Int): DomainHero = withContext(Dispatchers.IO) {
        marvelDao.findHeroById(id).toDomainHero()
    }

    override suspend fun update(hero: DomainHero) {
        withContext(Dispatchers.IO) { marvelDao.updateHero(hero.toRoomHero()) }
    }
}
