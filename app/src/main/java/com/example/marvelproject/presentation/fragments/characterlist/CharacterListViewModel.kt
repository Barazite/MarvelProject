package com.example.marvelproject.presentation.fragments.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {

    fun requestInformation() {
        viewModelScope.launch {
            MarvelRepository().getAllCharacters()
        }
    }
}