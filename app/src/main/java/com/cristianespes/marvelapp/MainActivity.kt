package com.cristianespes.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val heros = MarvelDb.service.listHeros(BuildConfig.API_TS, BuildConfig.API_KEY, BuildConfig.API_HASH).await()
            toast(heros.data?.results?.firstOrNull().toString())
        }
    }
}
