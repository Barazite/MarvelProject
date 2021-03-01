package com.example.marvelproject.data.marvel.repository.network

import com.example.marvelproject.data.marvel.model.ResponseAllCharactersDataModel
import com.example.marvelproject.data.marvel.model.ResponseCharacterDataModel
import com.example.marvelproject.data.marvel.model.ResponseGetComicDataModel
import javax.inject.Inject

class MarvelNetwork @Inject constructor(private val service: MarvelService){

    suspend fun getAllCharacters(limit: Int): ResponseAllCharactersDataModel{
        return service.getAllCharacters(limit)
    }

    suspend fun getCharacter(characterid: Int): ResponseCharacterDataModel {
        return service.getCharacter(characterid)
    }

    suspend fun getComic(comicId: Int): ResponseGetComicDataModel {
        return service.getComic(comicId)
    }







}