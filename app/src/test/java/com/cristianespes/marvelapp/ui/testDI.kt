package com.cristianespes.marvelapp.ui

import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.domain.Hero
import com.cristianespes.marvelapp.data.database.MarvelDatabase
import com.cristianespes.marvelapp.dataModule
import com.cristianespes.testshared.mockedHero
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule, dataModule) + modules)
    }
}

private val mockedAppModule = module {
    single { MarvelDatabase.build(get()) }
    factory<LocalDataSource> { FakeLocalDataSource() }
    factory<RemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val defaultFakeHeroes = listOf(
    mockedHero.copy(1),
    mockedHero.copy(2),
    mockedHero.copy(3),
    mockedHero.copy(4)
)

class FakeLocalDataSource : LocalDataSource {

    var heroes: List<Hero> = emptyList()

    override suspend fun isEmpty(): Boolean = heroes.isEmpty()

    override suspend fun saveHeroes(heroes: List<Hero>) {
        this.heroes = heroes
    }

    override suspend fun getHeroes(): List<Hero> = heroes

    override suspend fun findHeroById(id: Int): Hero = heroes.first { it.id == id }

    override suspend fun update(hero: Hero) {
        heroes = heroes.filterNot { it.id == hero.id } + hero
    }

}

class FakeRemoteDataSource : RemoteDataSource {

    var heroes = defaultFakeHeroes

    override suspend fun getHeroes(tsKey: String, apiKey: String, apiHash: String, offset: Int): List<Hero> = heroes
}
