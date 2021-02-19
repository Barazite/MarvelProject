package com.example.marvelproject.presentation.fragments.characterdetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.NoCharacterException
import com.example.marvelproject.databinding.CharacterDetailFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    lateinit var binding: CharacterDetailFragmentBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater,container, false)


        viewModel.getState().observe(viewLifecycleOwner, {state ->
            when(state){
                is BaseState.Normal ->{
                    onNormal(state.data as CharacterDetailState)
                }
                is BaseState.Error ->{
                    onError(state.dataError)
                }
                is BaseState.Loading ->{
                    onLoading(state.dataLoading)
                }
            }
        })

        setupView()
        viewModel.requestInformation(args.characterId)

        return binding.root
    }

    private fun setupView() {

    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onError(dataError: Throwable) {
        when (dataError){
            is NoCharacterException ->{

            }
            else ->{

            }
        }

    }

    private fun onNormal(characterDetailState: CharacterDetailState) {
        characterDetailState.character?.let{character ->
            binding.tvCharacterDetailName.text = character.name
            binding.tvCharacterDetailDescription.text = character.description

            character.urls.firstOrNull()?.let{link ->
                binding.myWebView.loadUrl(link.url.replace("http", "https"))
                binding.myWebView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return false
                    }
                }
            }
            // ViewPager2
            binding.vpCharacterDetail.adapter = CharacterDetailViewPagerAdapter(this, character)
            //TabLayout
            TabLayoutMediator(binding.tabCharacterDetail, binding.vpCharacterDetail) { tab, position ->
                tab.text = when(position) {
                    0 -> getString(R.string.comics_tab)
                    1 -> getString(R.string.series_tab)
                    2 -> getString(R.string.stories_tab)
                    else -> ""
                }
            }.attach()
        }
    }
}