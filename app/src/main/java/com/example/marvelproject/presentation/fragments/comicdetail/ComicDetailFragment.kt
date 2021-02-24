package com.example.marvelproject.presentation.fragments.comicdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.databinding.ComicDetailFragmentBinding

class ComicDetailFragment: BaseFragment<ComicDetailState, ComicDetailViewModel, ComicDetailFragmentBinding>(){

    override val viewModelClass: Class<ComicDetailViewModel> = ComicDetailViewModel::class.java
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ComicDetailFragmentBinding = ComicDetailFragmentBinding::inflate



    override fun setupView(viewModel: ComicDetailViewModel) {

    }

    override fun onNormal(data: ComicDetailState) {

    }

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(dataError: Throwable) {

    }
}