package com.cristianespes.marvelapp.ui.detail

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.ToggleHeroFavorite
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val heroId: Int) {

    @Provides
    fun detailViewModelProvider(
        findHeroById: FindHeroById,
        toggleHeroFavorite: ToggleHeroFavorite
    ): DetailViewModel {
        return DetailViewModel(heroId, findHeroById, toggleHeroFavorite)
    }

    @Provides
    fun findHeroByIdProvider(marvelRepository: MarvelRepository) = FindHeroById(marvelRepository)

    @Provides
    fun toggleMovieFavoriteProvider(marvelRepository: MarvelRepository) =
        ToggleHeroFavorite(marvelRepository)
}

@Subcomponent(modules = [(DetailActivityModule::class)])
interface DetailActivityComponent {
    val detaiViewModel: DetailViewModel
}