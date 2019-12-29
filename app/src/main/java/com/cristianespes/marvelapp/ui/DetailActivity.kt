package com.cristianespes.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.model.Character
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val HERO = "DetailActivity:hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getParcelableExtra<Character>(HERO)?.run {
            heroDetailToolbar.title = name

            heroDetailImage.loadUrl(thumbnail?.path.plus(".${thumbnail?.extension}"))

            heroDetailSummary.text = description

            heroDetailInfo.setHero(this)
        }
    }
}
