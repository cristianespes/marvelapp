package com.cristianespes.marvelapp.ui.main

import android.os.Bundle
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.MarvelRepository
import com.cristianespes.marvelapp.ui.common.CoroutineScopeActivity
import com.cristianespes.marvelapp.ui.common.startActivity
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import com.cristianespes.marvelapp.ui.main.HerosAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity() {

    private val marvelRepository: MarvelRepository by lazy { MarvelRepository() }

    private val herosAdapter = HerosAdapter {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.HERO, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewHeros.adapter = herosAdapter

        launch {
            herosAdapter.heroes = marvelRepository.findPopularHeroes().data?.results ?: emptyList()
        }
    }
}
