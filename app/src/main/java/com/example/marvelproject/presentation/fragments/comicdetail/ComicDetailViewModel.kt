package com.example.marvelproject.presentation.fragments.comicdetail

import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository

class ComicDetailViewModel: BaseViewModel<ComicDetailState>() {

    override val defaultState: ComicDetailState = ComicDetailState()

    override fun onStartFirstTime() {

    }

    fun requestInformation(id: Int) {
        updateToLoadingState()
        executeCoroutines({
            val comic = MarvelRepository().getComic(id)
            updateToNormalState(ComicDetailState(comic))
        },{ error ->
            updateToErrorState(error)
        })
    }

}