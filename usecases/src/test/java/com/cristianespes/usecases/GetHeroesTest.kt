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
class GetHeroesTest {

    @Mock
    lateinit var marvelRepository: MarvelRepository

    lateinit var getHeroes: GetHeroes

    @Before
    fun setUp() {
        getHeroes = GetHeroes(marvelRepository)
    }

    @Test
    fun `invoke calls heroes repository`() {
        runBlocking {

            val movies = listOf(mockedHero.copy(id = 1))
            whenever(marvelRepository.getHeroes()).thenReturn(movies)

            val result = getHeroes.invoke()

            Assert.assertEquals(movies, result)
        }
    }
}