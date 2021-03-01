package com.example.marvelproject.data.marvel.repository.network

import com.example.marvelproject.data.marvel.model.ResponseAllCharactersDataModel
import com.example.marvelproject.data.marvel.model.ResponseCharacterDataModel
import com.example.marvelproject.data.marvel.model.ResponseGetComicDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(@Query("limit") limit: Int): ResponseAllCharactersDataModel

    @GET("v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") characterid: Int): ResponseCharacterDataModel

    @GET("v1/public/comics/{comicId}")
    suspend fun getComic(@Path("comicId") comicId: Int): ResponseGetComicDataModel
}