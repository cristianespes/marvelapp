package com.cristianespes.marvelapp.ui.main

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.usecases.GetHeroes
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getHeroes: GetHeroes) = MainViewModel(getHeroes)

    @Provides
    fun getHeroesProvider(marvelRepository: MarvelRepository) =
        GetHeroes(marvelRepository)
}

@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}