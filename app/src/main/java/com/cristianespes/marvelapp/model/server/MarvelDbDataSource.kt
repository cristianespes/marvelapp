package com.cristianespes.marvelapp.model.server

import com.cristianespes.data.RemoteDataSource
import com.cristianespes.domain.Hero
import com.cristianespes.marvelapp.model.toDomainHero
import com.cristianespes.marvelapp.model.toRoomHero

class MarvelDbDataSource: RemoteDataSource {
    override suspend fun getHeroes(tsKey: String, apiKey: String, apiHash: String): List<Hero> {
        return MarvelDb.service.listHeroesAsync(
            tsKey,
            apiKey,
            apiHash,
            80
        ).await().data?.results?.map(Character::toRoomHero)?.map { it.toDomainHero() } ?: emptyList()
    }
}
