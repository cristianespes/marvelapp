package com.cristianespes.marvelapp.ui

import android.os.Bundle
import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.MarvelDb
import com.cristianespes.marvelapp.ui.common.CoroutineScopeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity() {

    private val herosAdapter = HerosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewHeros.adapter = herosAdapter

        launch {
            val heros = MarvelDb.service.listHerosAsync(
                BuildConfig.API_TS,
                BuildConfig.API_KEY,
                BuildConfig.API_HASH
            ).await()
            herosAdapter.heroes = heros.data?.results ?: emptyList()
        }
    }
}
