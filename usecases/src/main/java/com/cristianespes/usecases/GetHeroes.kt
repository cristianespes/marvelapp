package com.cristianespes.usecases

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.domain.Hero

class GetHeroes(private val marvelRepository: MarvelRepository) {
    suspend fun invoke(): List<Hero> = marvelRepository.getHeroes()
}
