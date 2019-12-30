package com.cristianespes.marvelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.server.MarvelRepository
import com.cristianespes.marvelapp.ui.common.getViewModel
import com.cristianespes.marvelapp.ui.common.startActivity
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import com.cristianespes.marvelapp.ui.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var heroesAdapter: HeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModel { MainViewModel(MarvelRepository()) }

        heroesAdapter = HeroesAdapter(viewModel::onMovieClicked)
        recyclerViewHeroes.adapter = heroesAdapter

        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                startActivity<DetailActivity> {
                    putExtra(DetailActivity.HERO, it)
                }
            }
        })
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model == UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> heroesAdapter.heroes = model.heros
        }
    }
}
