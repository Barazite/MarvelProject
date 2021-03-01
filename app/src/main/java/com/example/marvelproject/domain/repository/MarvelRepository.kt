package com.example.marvelproject.domain.repository

import com.example.marvelproject.data.marvel.model.Character
import com.example.marvelproject.data.marvel.model.Comic


interface MarvelRepository {

    suspend fun getAllCharacters(limit: Int): List<Character>
    suspend fun getCharacter(characterid: Int): Character
    suspend fun getComic(comicId: Int): Comic
}