package com.cristianespes.marvelapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
