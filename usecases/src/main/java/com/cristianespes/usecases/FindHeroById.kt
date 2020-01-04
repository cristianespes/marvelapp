package com.cristianespes.usecases

import com.cristianespes.data.MarvelRepository
import com.cristianespes.domain.Hero


class FindHeroById(
    private val marvelRepository: MarvelRepository
) {
    suspend fun invoke(id: Int): Hero = marvelRepository.findHeroById(id)
}