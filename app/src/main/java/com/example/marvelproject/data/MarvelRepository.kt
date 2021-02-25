package com.example.marvelproject.data

import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.model.Comic
import com.example.marvelproject.data.network.MarvelNetwork


class MarvelRepository {
    suspend fun getAllCharacters(limit: Int): List<Character> {
        return MarvelNetwork().getAllCharacters(limit).data.results
    }

    suspend fun getCharacter(characterid: Int): Character {
        val response = MarvelNetwork().getCharacter(characterid).data.results
        return if (response.isNotEmpty()) response[0] else throw NoCharacterException()
    }

    suspend fun getComic(comicId: Int): Comic {
        return MarvelNetwork().getComic(comicId).data.results.first()
    }

}

class NoCharacterException : Exception()
