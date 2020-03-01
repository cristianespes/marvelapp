package com.cristianespes.marvelapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristianespes.usecases.GetHeroes
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.cristianespes.marvelapp.ui.main.MainViewModel.UiModel
import com.cristianespes.testshared.mockedHero
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getHeroes: GetHeroes

    @Mock
    lateinit var observer: Observer<UiModel>

    private lateinit var vm: MainViewModel


    @Before
    fun setUp() {
        vm = MainViewModel(getHeroes, Dispatchers.Unconfined)
    }

    @Test
    fun `after initialize, loading is shown`() {
        runBlocking {
            vm.model.observeForever(observer)

            verify(observer).onChanged(UiModel.Loading)
        }
    }

    @Test
    fun `after initialize, getHeroes is called`() {
        runBlocking {
            val heroes = listOf(mockedHero.copy(id = 1))

            vm.model.observeForever(observer)

            verify(observer).onChanged(UiModel.Loading)

            whenever(getHeroes.invoke()).thenReturn(heroes)
            verify((observer).onChanged(UiModel.Content(heroes)))
        }
    }

}