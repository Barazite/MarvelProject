package com.example.marvelproject.presentation.fragments.comicdetail

import com.example.marvelproject.base.BaseViewState
import com.example.marvelproject.data.model.Comic

data class ComicDetailState (
    val comic: Comic? = null
): BaseViewState()