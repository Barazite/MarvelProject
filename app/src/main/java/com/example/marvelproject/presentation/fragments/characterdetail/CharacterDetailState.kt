package com.example.marvelproject.presentation.fragments.characterdetail

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterDetailState(
    val character: Character? = null
): BaseViewState()
