package com.cristianespes.marvelapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cristianespes.domain.Hero
import com.cristianespes.marvelapp.ui.common.ScopedViewModel
import com.cristianespes.usecases.FindHeroById
import com.cristianespes.usecases.ToggleHeroFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val heroId: Int,
    private val findHeroById: FindHeroById,
    private val toggleHeroFavorite: ToggleHeroFavorite,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    data class UiModel(val hero: Hero)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findHero()
            return _model
        }

    fun onFavoriteClicked() = launch {
        _model.value?.hero?.let {
            _model.value = UiModel(toggleHeroFavorite.invoke(it))
        }
    }

    private fun findHero() = launch {
        _model.value = UiModel(findHeroById.invoke(heroId))
    }
}
