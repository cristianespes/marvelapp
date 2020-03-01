package com.cristianespes.marvelapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.marvelapp.ui.FakeLocalDataSource
import com.cristianespes.marvelapp.ui.defaultFakeHeroes
import com.cristianespes.marvelapp.ui.initMockedDi
import com.cristianespes.testshared.mockedHero
import com.cristianespes.usecases.GetHeroes
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { MainViewModel(get(), get()) }
            factory { GetHeroes(get()) }
        }

        initMockedDi(vmModule)
        vm = get()
    }

    @Test
    fun `data is loaded from server when local source is empty`() {
        vm.model.observeForever(observer)

        verify(observer).onChanged(MainViewModel.UiModel.Content(defaultFakeHeroes))
    }

    @Test
    fun `data is loaded from local source when available`() {
        val fakeLocalMovies = listOf(mockedHero.copy(10), mockedHero.copy(11))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.heroes = fakeLocalMovies
        vm.model.observeForever(observer)

        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeLocalMovies))
    }
}