package com.cristianespes.marvelapp.ui.main

import com.cristianespes.marvelapp.model.Character
import com.cristianespes.marvelapp.model.MarvelRepository
import com.cristianespes.marvelapp.ui.common.Scope
import kotlinx.coroutines.launch

class MainPresenter(private val marvelRepository: MarvelRepository) : Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(heroes: List<Character>)
        fun navigateTo(hero: Character)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()

        this.view = view

        launch {
            view.showProgress()
            view.updateData(marvelRepository.findPopularHeroes().data?.results ?: emptyList())
            view.hideProgress()
        }
    }

    fun onMovieClicked(hero: Character) {
        view?.navigateTo(hero)
    }

    fun onDestroy() {
        this.view = null

        destroyScope()
    }
}