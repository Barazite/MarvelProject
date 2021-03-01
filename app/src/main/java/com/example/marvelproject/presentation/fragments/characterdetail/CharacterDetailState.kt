package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.marvel.model.Character

data class CharacterDetailState(
    val character: Character? = null
): BaseViewState()
