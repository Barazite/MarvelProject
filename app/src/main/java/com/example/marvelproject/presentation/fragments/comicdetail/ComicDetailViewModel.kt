package com.example.marvelproject.presentation.fragments.comicdetail

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(private val repository: MarvelRepository): BaseViewModel<ComicDetailState>() {

    override val defaultState: ComicDetailState = ComicDetailState()

    override fun onStartFirstTime() {}

    fun requestInformation(id: Int) {
        updateToLoadingState()
        executeCoroutines({
            val comic = repository.getComic(id)
            updateToNormalState(ComicDetailState(comic))
        },{ error ->
            updateToErrorState(error)
        })
    }

}