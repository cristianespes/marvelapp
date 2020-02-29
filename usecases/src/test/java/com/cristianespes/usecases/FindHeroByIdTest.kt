package com.cristianespes.usecases

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.testshared.mockedHero
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FindHeroByIdTest {

    @Mock
    lateinit var marvelRepository: MarvelRepository

    lateinit var findHeroById: FindHeroById

    @Before
    fun setUp() {
        findHeroById = FindHeroById(marvelRepository)
    }

    @Test
    fun `invoke calls heroes repository`() {
        runBlocking {

            val hero = mockedHero.copy(id = 1)
            whenever(marvelRepository.findHeroById(1)).thenReturn(hero)

            val result = findHeroById.invoke(1)

            Assert.assertEquals(hero, result)
        }
    }
}