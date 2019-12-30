package com.cristianespes.marvelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.Character
import com.cristianespes.marvelapp.model.MarvelRepository
import com.cristianespes.marvelapp.ui.common.startActivity
import com.cristianespes.marvelapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(MarvelRepository()) }
    private val herosAdapter = HerosAdapter(presenter::onMovieClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)

        recyclerViewHeros.adapter = herosAdapter
    }

    override fun onDestroy() {
        presenter.onDestroy()

        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun updateData(heroes: List<Character>) {
        herosAdapter.heroes = heroes
    }

    override fun navigateTo(hero: Character) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.HERO, hero)
        }
    }
}
