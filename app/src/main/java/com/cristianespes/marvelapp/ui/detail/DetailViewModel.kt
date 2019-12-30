package com.cristianespes.marvelapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cristianespes.marvelapp.model.Character

class DetailViewModel(private val hero: Character) : ViewModel() {

    class UiModel(val hero: Character)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(hero)
            return _model
        }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val hero: Character) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(hero) as T
}