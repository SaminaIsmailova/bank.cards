package com.example.jetpack.ui.home

import androidx.lifecycle.ViewModel
import com.example.jetpack.Repository
import com.example.jetpack.model.CardsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cardsLis= mutableListOf<CardsListModel>()
    val cardsList :List<CardsListModel> get() = _cardsLis

    init {
        _cardsLis.addAll(repository.getData())
    }
}
