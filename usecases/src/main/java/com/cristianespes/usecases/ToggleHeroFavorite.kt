package com.cristianespes.usecases

import com.cristianespes.data.MarvelRepository
import com.cristianespes.domain.Hero

class ToggleHeroFavorite(
    private val marvelRepository: MarvelRepository
) {
    suspend fun invoke(hero: Hero): Hero = with(hero) {
        copy(favorite = !favorite).also { marvelRepository.update(it) }
    }
}