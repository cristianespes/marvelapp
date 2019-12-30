package com.cristianespes.marvelapp.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.text.bold
import com.cristianespes.marvelapp.model.server.Character

class HeroDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun setHero(hero: Character) = with(hero) {
        text = androidx.core.text.buildSpannedString {
            bold { append("Release date: ") }
            appendln(modified.toString())
        }
    }
}