package com.cristianespes.marvelapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.cristianespes.data.MarvelRepository
import com.cristianespes.marvelapp.BuildConfig
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.database.RoomDataSource
import com.cristianespes.marvelapp.model.server.MarvelDbDataSource
import com.cristianespes.marvelapp.ui.common.app
import com.cristianespes.marvelapp.ui.common.getViewModel
import com.cristianespes.marvelapp.ui.common.loadUrl
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.ToggleHeroFavorite
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val HERO = "DetailActivity:hero"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = getViewModel {
            val localDataSource = RoomDataSource(app.db)
            val remoteDataSource = MarvelDbDataSource()

            val marvelRepository = MarvelRepository(
                localDataSource,
                remoteDataSource,
                BuildConfig.API_TS,
                BuildConfig.API_KEY,
                BuildConfig.API_HASH
            )

            DetailViewModel(
                intent.getIntExtra(HERO, -1),
                FindHeroById(marvelRepository),
                ToggleHeroFavorite(marvelRepository)
            )
        }


        viewModel.model.observe(this, Observer(::updateUi))

        heroDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model.hero) {
        heroDetailToolbar.title = name
        heroDetailImage.loadUrl(thumbnail ?: "")
        heroDetailSummary.text = description
        heroDetailInfo.setHero(this)

        val icon = if (favorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        heroDetailFavorite.setImageDrawable(getDrawable(icon))
    }
}
