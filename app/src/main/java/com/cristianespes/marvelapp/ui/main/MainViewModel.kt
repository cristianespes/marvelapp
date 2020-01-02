package com.cristianespes.marvelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cristianespes.marvelapp.model.database.Hero
import com.cristianespes.marvelapp.model.server.MarvelRepository
import com.cristianespes.marvelapp.ui.common.Event
import com.cristianespes.marvelapp.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val marvelRepository: MarvelRepository
) : ScopedViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val heroes: List<Hero>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<Int>>()
    val navigation: LiveData<Event<Int>> = _navigation

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(marvelRepository.findPopularHeroes())
        }
    }

    fun onMovieClicked(hero: Hero) {
        _navigation.value = Event(hero.id)
    }

    override fun onCleared() {
        destroyScope()

        super.onCleared()
    }
}
