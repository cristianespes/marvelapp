package com.cristianespes.marvelapp.data.server

import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.domain.Hero
import com.cristianespes.marvelapp.data.toDomainHero
import com.cristianespes.marvelapp.data.toRoomHero

class MarvelDbDataSource: RemoteDataSource {
    override suspend fun getHeroes(tsKey: String, apiKey: String, apiHash: String, offset: Int): List<Hero> {
        return MarvelDb.service.listHeroesAsync(
            tsKey,
            apiKey,
            apiHash,
            100,
            offset
        ).await().data?.results?.map(Character::toRoomHero)?.map { it.toDomainHero() } ?: emptyList()
    }
}
