package com.cristianespes.marvelapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.marvelapp.ui.FakeLocalDataSource
import com.cristianespes.marvelapp.ui.defaultFakeHeroes
import com.cristianespes.marvelapp.ui.initMockedDi
import com.cristianespes.testshared.mockedHero
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.ToggleHeroFavorite
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailIntegrationTests: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<DetailViewModel.UiModel>

    private lateinit var vm: DetailViewModel
    private lateinit var localDataSource: FakeLocalDataSource

    @Before
    fun setUp() {
        val vmModule = module {
            factory { (id: Int) -> DetailViewModel(id, get(), get(), get()) }
            factory { FindHeroById(get()) }
            factory { ToggleHeroFavorite(get()) }
        }

        initMockedDi(vmModule)
        vm = get { parametersOf(1) }

        localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.heroes = defaultFakeHeroes
    }

    @Test
    fun `observing LiveData finds the movie`() {
        vm.model.observeForever(observer)

        verify(observer).onChanged(DetailViewModel.UiModel(mockedHero.copy(1)))
    }

    @Test
    fun `favorite is updated in local data source`() {
        vm.model.observeForever(observer)

        vm.onFavoriteClicked()

        runBlocking {
            Assert.assertTrue(localDataSource.findHeroById(1).favorite)
        }
    }
}