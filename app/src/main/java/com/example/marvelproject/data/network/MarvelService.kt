package com.example.marvelproject.data.network

import com.example.marvelproject.data.model.ResponseAllCharactersDataModel
import retrofit2.http.GET

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getAllCharacter(): ResponseAllCharactersDataModel
}