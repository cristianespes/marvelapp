package com.cristianespes.marvelapp

import android.app.Application
import com.cristianespes.marvelapp.di.DaggerMarvelComponent
import com.cristianespes.marvelapp.di.MarvelComponent
import com.facebook.stetho.Stetho

class MarvelApp : Application() {

    lateinit var component: MarvelComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMarvelComponent
            .factory()
            .create(this)

        Stetho.initializeWithDefaults(this)
    }

}