package com.cristianespes.data.repository

import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.domain.Hero
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MarvelRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var marvelRepository: MarvelRepository

    private val tsKey = "1kd57s"
    private val apiKey = "1a2b3c4d"
    private val apiHash = "d8m3jf"

    @Before
    fun setUp() {
        marvelRepository = MarvelRepository(localDataSource, remoteDataSource, tsKey, apiKey, apiHash)
    }

    @Test
    fun `getHeroes gets from local data source first`() {
        runBlocking {

            val localMovies = listOf(mockedHero.copy(1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getHeroes()).thenReturn(localMovies)

            val result = marvelRepository.getHeroes()

            Assert.assertEquals(localMovies, result)
        }
    }

    @Test
    fun `getHeroes saves remote data to local`() {
        runBlocking {

            val remoteHeroes = listOf(mockedHero.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getHeroes(any(), any(), any(), any())).thenReturn(remoteHeroes)

            marvelRepository.getHeroes()

            verify(localDataSource, times(15)).saveHeroes(remoteHeroes)
        }
    }

    @Test
    fun `findHeroById calls local data source`() {
        runBlocking {

            val movie = mockedHero.copy(id = 5)
            whenever(localDataSource.findHeroById(5)).thenReturn(movie)

            val result = marvelRepository.findHeroById(5)

            Assert.assertEquals(movie, result)
        }
    }

    @Test
    fun `update updates local data source`() {
        runBlocking {

            val movie = mockedHero.copy(id = 1)

            marvelRepository.update(movie)

            verify(localDataSource).update(movie)
        }
    }

    private val mockedHero = Hero(
        0,
        "Spiderman",
        "",
        null,
        null,
        false
    )

}