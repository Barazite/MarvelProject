package com.example.marvelproject.presentation.fragments.comicdetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.databinding.ComicDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment: BaseFragment<ComicDetailState, ComicDetailViewModel, ComicDetailFragmentBinding>(){

    override val viewModelClass: Class<ComicDetailViewModel> = ComicDetailViewModel::class.java
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ComicDetailFragmentBinding = ComicDetailFragmentBinding::inflate
    private val args: ComicDetailFragmentArgs by navArgs()

    lateinit var mViewModel: ComicDetailViewModel

    override fun setupView(viewModel: ComicDetailViewModel) {
        mViewModel = viewModel

        mViewModel.requestInformation(args.comicId)
    }

    override fun onNormal(data: ComicDetailState) {
        binding.pbComicDetail.visibility = View.GONE
        binding.tvComicDetailDescription.setTextColor(Color.BLACK)

        binding.tvComicDetailName.text = data.comic?.title ?: "No hay titulo"
        binding.tvComicDetailDescription.text = data.comic?.description ?: "No hay descripcion"
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.pbComicDetail.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        binding.tvComicDetailDescription.setTextColor(Color.RED)
        binding.tvComicDetailName.setTextColor(Color.RED)
        binding.tvComicDetailName.text = "ERROR"
        binding.tvComicDetailDescription.text = "Hubo un error al solicitar la informacion: \n $dataError"
    }
}