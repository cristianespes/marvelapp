package com.cristianespes.marvelapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.Character
import com.cristianespes.marvelapp.ui.common.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val HERO = "DetailActivity:hero"
    }

    private val presenter = DetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val hero: Character = intent.getParcelableExtra(HERO)
            ?: throw (IllegalStateException("Hero not found"))

        presenter.onCreate(this, hero)
    }

    override fun onDestroy() {
        presenter.onDestroy()

        super.onDestroy()
    }

    override fun updateUI(hero: Character) = with(hero) {
        heroDetailToolbar.title = name
        heroDetailImage.loadUrl(thumbnail?.path.plus(".${thumbnail?.extension}"))
        heroDetailSummary.text = description
        heroDetailInfo.setHero(this)
    }
}
