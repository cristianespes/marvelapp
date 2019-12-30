package com.cristianespes.marvelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cristianespes.marvelapp.model.Character
import com.cristianespes.marvelapp.model.MarvelRepository
import com.cristianespes.marvelapp.ui.common.Scope
import kotlinx.coroutines.launch

class MainViewModel(
    private val marvelRepository: MarvelRepository
) : ViewModel(), Scope by Scope.Impl() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val heros: List<Character>) : UiModel()
        class Navigation(val hero: Character) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    init {
        initScope()
    }

    private fun refresh() {

        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(marvelRepository.findPopularHeroes().data?.results ?: emptyList())
        }
    }

    fun onMovieClicked(hero: Character) {
        _model.value = UiModel.Navigation(hero)
    }

    override fun onCleared() {
        destroyScope()

        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val marvelRepository: MarvelRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(marvelRepository) as T
}
