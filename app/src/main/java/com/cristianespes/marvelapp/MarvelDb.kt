package com.cristianespes.marvelapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelDb {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val service: MarvelService = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run { create<MarvelService>(MarvelService::class.java) }

}


// http://gateway.marvel.com/v1/public/characters?ts=1&apikey=b7dd6e8183270dfcce0a8cb8eef7e06a&hash=0f4d5b77d0e53b80e42f2708eb2c3fc3
// https://gateway.marvel.com/v1/public/characters?ts=1&apiKey=b7dd6e8183270dfcce0a8cb8eef7e06a&hash=0f4d5b77d0e53b80e42f2708eb2c3fc3