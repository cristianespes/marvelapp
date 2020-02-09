package com.cristianespes.marvelapp.di

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.GetHeroes
import com.cristianespes.usecases.ToggleHeroFavorite
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getHeroesProvider(marvelRepository: MarvelRepository) =
        GetHeroes(marvelRepository)

    @Provides
    fun findHeroByIdProvider(marvelRepository: MarvelRepository) = FindHeroById(marvelRepository)

    @Provides
    fun toggleHeroFavoriteProvider(marvelRepository: MarvelRepository) =
        ToggleHeroFavorite(marvelRepository)

}