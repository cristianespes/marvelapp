package com.cristianespes.usecases

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.testshared.mockedHero
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ToggleHeroFavoriteTest {

    @Mock
    lateinit var marvelRepository: MarvelRepository

    lateinit var toggleHeroFavorite: ToggleHeroFavorite

    @Before
    fun setUp() {
        toggleHeroFavorite = ToggleHeroFavorite(marvelRepository)
    }

    @Test
    fun `invoke calls heroes repository`() {
        runBlocking {

            val movie = mockedHero.copy(id = 1)

            val result = toggleHeroFavorite.invoke(movie)

            verify(marvelRepository).update(result)
        }
    }

    @Test
    fun `favorite hero becomes unfavorite`() {
        runBlocking {

            val movie = mockedHero.copy(favorite = true)

            val result = toggleHeroFavorite.invoke(movie)

            Assert.assertFalse(result.favorite)
        }
    }

    @Test
    fun `unfavorite hero becomes favorite`() {
        runBlocking {

            val movie = mockedHero.copy(favorite = false)

            val result = toggleHeroFavorite.invoke(movie)

            Assert.assertTrue(result.favorite)
        }
    }
}