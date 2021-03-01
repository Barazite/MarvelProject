package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.domain.repository.MarvelRepository
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val repository: MarvelRepository): BaseViewModel<CharacterDetailState>() {


    override val defaultState: CharacterDetailState = CharacterDetailState()

    override fun onStartFirstTime() {

    }

    fun requestInformation(characterId: Int) {
        updateToLoadingState()
        executeCoroutines({
            val response = repository.getCharacter(characterId)
            updateToNormalState(CharacterDetailState(response))
        },{ error ->
            updateToErrorState(error)
        })
    }


}