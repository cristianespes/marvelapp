package com.cristianespes.marvelapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cristianespes.marvelapp.model.database.Hero
import com.cristianespes.marvelapp.model.server.MarvelRepository
import com.cristianespes.marvelapp.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val heroId: Int, private val marvelRepository: MarvelRepository) : ScopedViewModel() {

    class UiModel(val hero: Hero)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findHero()
            return _model
        }

    fun onFavoriteClicked() = launch {
        _model.value?.hero?.let {
            val updatedHero = it.copy(favorite = !it.favorite)
            _model.value = UiModel(updatedHero)
            marvelRepository.update(updatedHero)
        }
    }

    private fun findHero() = launch {
            _model.value = UiModel(marvelRepository.findById(heroId))
        }
}
