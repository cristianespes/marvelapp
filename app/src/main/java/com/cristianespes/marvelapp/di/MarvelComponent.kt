package com.cristianespes.marvelapp.di

import android.app.Application
import com.cristianespes.marvelapp.ui.detail.DetailViewModel
import com.cristianespes.marvelapp.ui.main.MainViewModel
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelsModule::class])
interface MarvelComponent {

    val mainViewModel: MainViewModel
    val detaiViewModel: DetailViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MarvelComponent
    }
}