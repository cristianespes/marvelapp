package com.cristianespes.marvelapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.Character
import com.cristianespes.marvelapp.ui.common.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity() {

    companion object {
        const val HERO = "DetailActivity:hero"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val hero: Character = intent.getParcelableExtra(HERO)
            ?: throw (IllegalStateException("Hero not found"))

        viewModel = ViewModelProviders.of(
            this,
            DetailViewModelFactory(hero)
        )[DetailViewModel::class.java]

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model.hero) {
        heroDetailToolbar.title = name
        heroDetailImage.loadUrl(thumbnail?.path.plus(".${thumbnail?.extension}"))
        heroDetailSummary.text = description
        heroDetailInfo.setHero(this)
    }
}
