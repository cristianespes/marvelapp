package com.cristianespes.marvelapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristianespes.testshared.mockedHero
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.ToggleHeroFavorite
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var findHeroById: FindHeroById

    @Mock
    lateinit var toggleHeroFavorite: ToggleHeroFavorite

    @Mock
    lateinit var observer: Observer<DetailViewModel.UiModel>

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        vm = DetailViewModel(1, findHeroById, toggleHeroFavorite, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData finds the hero`() {
        runBlocking {
            val hero = mockedHero.copy(id = 1)
            whenever(findHeroById.invoke(1)).thenReturn(hero)

            vm.model.observeForever(observer)

            verify(observer).onChanged(DetailViewModel.UiModel(hero))
        }
    }

    @Test
    fun `when favorite clicked, the toggleHeroFavorite use case is invoked`() {
        runBlocking {
            val hero = mockedHero.copy(id = 1)
            whenever(findHeroById.invoke(1)).thenReturn(hero)
            whenever(toggleHeroFavorite.invoke(hero)).thenReturn(hero.copy(favorite = !hero.favorite))
            vm.model.observeForever(observer)

            vm.onFavoriteClicked()

            verify(toggleHeroFavorite).invoke(hero)
        }
    }
}