package com.example.marvelproject.di


import com.example.marvelproject.data.config.MarvelRetrofit
import com.example.marvelproject.data.marvel.repository.MarvelRepositoryImpl
import com.example.marvelproject.data.marvel.repository.network.MarvelNetwork
import com.example.marvelproject.data.marvel.repository.network.MarvelService
import com.example.marvelproject.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMarvelRepository(retrofit: MarvelRetrofit): MarvelRepository = MarvelRepositoryImpl(MarvelNetwork(retrofit.loadRetrofit().create(MarvelService::class.java)))


}
