package com.example.marvelproject.data

import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.network.MarvelNetwork


class MarvelRepository {
    suspend fun getAllCharacters(limit: Int): List<Character>{
        return MarvelNetwork().getAllCharacters(limit).data.results
    }
}