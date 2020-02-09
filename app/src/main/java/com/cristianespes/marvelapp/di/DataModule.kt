package com.cristianespes.marvelapp.di

import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.data.source.LocalDataSource
import com.cristianespes.data.source.RemoteDataSource
import com.cristianespes.marvelapp.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun marvelRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = MarvelRepository(localDataSource, remoteDataSource, BuildConfig.API_TS, BuildConfig.API_KEY, BuildConfig.API_HASH)

}