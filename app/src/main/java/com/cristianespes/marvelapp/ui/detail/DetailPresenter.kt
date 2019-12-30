package com.cristianespes.marvelapp.ui.detail

import com.cristianespes.marvelapp.model.Character

class DetailPresenter {

    private var view: View? = null

    interface View {
        fun updateUI(hero: Character)
    }

    fun onCreate(view: View, hero: Character) {
        this.view = view
        view.updateUI(hero)
    }

    fun onDestroy() {
        view = null
    }
}