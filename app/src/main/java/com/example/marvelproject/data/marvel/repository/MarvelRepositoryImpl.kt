package com.example.marvelproject.data.marvel.repository

import com.example.marvelproject.data.marvel.model.Character
import com.example.marvelproject.data.marvel.model.Comic
import com.example.marvelproject.data.marvel.repository.network.MarvelNetwork
import com.example.marvelproject.domain.repository.MarvelRepository


class MarvelRepositoryImpl constructor(private val network: MarvelNetwork): MarvelRepository{

    override suspend fun getAllCharacters(limit: Int): List<Character> {
        return network.getAllCharacters(limit).data.results
    }

    override suspend fun getCharacter(characterid: Int): Character {
        val response = network.getCharacter(characterid).data.results
        return if (response.isNotEmpty()) response[0] else throw NoCharacterException()
    }

    override suspend fun getComic(comicId: Int): Comic {
        return network.getComic(comicId).data.results.first()
    }

}

class NoCharacterException : Exception()
