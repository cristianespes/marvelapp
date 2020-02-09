package com.cristianespes.marvelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.ui.common.EventObserver
import com.cristianespes.marvelapp.ui.common.startActivity
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import com.cristianespes.marvelapp.ui.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var heroesAdapter: HeroesAdapter

    private val viewModel: MainViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
