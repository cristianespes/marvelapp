package com.cristianespes.marvelapp

import android.app.Application
import com.facebook.stetho.Stetho

class MarvelApp : Application() {


    override fun onCreate() {
        super.onCreate()

        initDI()

        Stetho.initializeWithDefaults(this)
    }

}