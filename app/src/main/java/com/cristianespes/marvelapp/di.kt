package com.cristianespes.marvelapp

import android.app.Application
import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.marvelapp.data.database.MarvelDatabase
import com.cristianespes.marvelapp.data.database.RoomDataSource
import com.cristianespes.marvelapp.data.server.MarvelDb
import com.cristianespes.marvelapp.data.server.MarvelDbDataSource
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import com.cristianespes.marvelapp.ui.detail.DetailViewModel
import com.cristianespes.marvelapp.ui.main.MainActivity
import com.cristianespes.marvelapp.ui.main.MainViewModel
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.GetHeroes
import com.cristianespes.usecases.ToggleHeroFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { MarvelDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { MarvelDbDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://gateway.marvel.com/v1/public/" }
    single { MarvelDb(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { MarvelRepository(get(), get(), BuildConfig.API_TS, BuildConfig.API_KEY, BuildConfig.API_HASH) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetHeroes(get()) }
    }

    scope(named<DetailActivity>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get(), get()) }
        scoped { FindHeroById(get()) }
        scoped { ToggleHeroFavorite(get()) }
    }
}