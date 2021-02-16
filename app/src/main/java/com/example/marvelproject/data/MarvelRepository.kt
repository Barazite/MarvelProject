package com.example.marvelproject.data

import com.example.marvelproject.data.model.ResponseAllCharactersDataModel
import com.example.marvelproject.data.network.MarvelNetwork


class MarvelRepository {
    suspend fun getAllCharacters(): ResponseAllCharactersDataModel{
        return MarvelNetwork().getAllCharacter()
    }
}