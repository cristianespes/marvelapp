package com.cristianespes.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val herosAdapter = HerosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewHeros.adapter = herosAdapter

        GlobalScope.launch(Dispatchers.Main) {
            val heros = MarvelDb.service.listHeros(BuildConfig.API_TS, BuildConfig.API_KEY, BuildConfig.API_HASH).await()
            herosAdapter.heroes = heros.data?.results ?: emptyList()
        }
    }
}
