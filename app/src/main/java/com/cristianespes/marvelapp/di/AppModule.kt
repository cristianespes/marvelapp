package com.cristianespes.marvelapp.di

import android.app.Application
import androidx.room.Room
import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.marvelapp.data.database.MarvelDatabase
import com.cristianespes.marvelapp.data.database.RoomDataSource
import com.cristianespes.marvelapp.data.server.MarvelDbDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        MarvelDatabase::class.java,
        "marvel-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: MarvelDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = MarvelDbDataSource()
}