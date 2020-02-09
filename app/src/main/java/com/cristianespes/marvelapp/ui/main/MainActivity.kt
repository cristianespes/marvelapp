package com.cristianespes.marvelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cristianespes.data.repository.MarvelRepository
import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.data.database.RoomDataSource
import com.cristianespes.marvelapp.data.server.MarvelDbDataSource
import com.cristianespes.marvelapp.ui.common.EventObserver
import com.cristianespes.marvelapp.ui.common.app
import com.cristianespes.marvelapp.ui.common.getViewModel
import com.cristianespes.marvelapp.ui.common.startActivity
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import com.cristianespes.marvelapp.ui.main.MainViewModel.UiModel
import com.cristianespes.usecases.GetHeroes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var heroesAdapter: HeroesAdapter

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())

        heroesAdapter = HeroesAdapter(viewModel::onMovieClicked)
        recyclerViewHeroes.adapter = heroesAdapter

        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, EventObserver { id ->
            startActivity<DetailActivity> {
                putExtra(DetailActivity.HERO, id)
            }
        })
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model == UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> heroesAdapter.heroes = model.heroes
        }
    }
}
