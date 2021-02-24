package com.example.marvelproject.presentation.fragments.characterlist

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterListState(

    val characterList: List<Character> = listOf(),
    val limit: Int = 20,
    //val character: Character? = null
): BaseViewState()
