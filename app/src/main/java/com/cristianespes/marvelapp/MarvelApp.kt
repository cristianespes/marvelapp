package com.cristianespes.marvelapp

import android.app.Application
import androidx.room.Room
import com.cristianespes.marvelapp.data.database.MarvelDatabase
import com.facebook.stetho.Stetho

class MarvelApp : Application() {

    lateinit var db: MarvelDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            MarvelDatabase::class.java, "marvel-db"
        ).build()

        Stetho.initializeWithDefaults(this)
    }
}