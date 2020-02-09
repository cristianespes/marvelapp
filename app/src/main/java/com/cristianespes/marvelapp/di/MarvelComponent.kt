package com.cristianespes.marvelapp.di

import android.app.Application
import com.cristianespes.marvelapp.ui.detail.DetailActivityComponent
import com.cristianespes.marvelapp.ui.detail.DetailActivityModule
import com.cristianespes.marvelapp.ui.main.MainActivityComponent
import com.cristianespes.marvelapp.ui.main.MainActivityModule
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MarvelComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule) : DetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MarvelComponent
    }
}