package com.cristianespes.marvelapp.di

import com.cristianespes.marvelapp.ui.detail.DetailViewModel
import com.cristianespes.marvelapp.ui.main.MainViewModel
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.GetHeroes
import com.cristianespes.usecases.ToggleHeroFavorite
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {

    @Provides
    fun mainViewModelProvider(getHeroes: GetHeroes) = MainViewModel(getHeroes)

    @Provides
    fun detailViewModelProvider(
        findHeroById: FindHeroById,
        toggleHeroFavorite: ToggleHeroFavorite
    ): DetailViewModel {
        // TODO the id needs to be provided. We'll see it with scoped graphs
        return DetailViewModel(-1, findHeroById, toggleHeroFavorite)
    }

}