package com.example.marvelproject.presentation.fragments.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.data.NoCharacterException
import com.example.marvelproject.databinding.CharacterDetailFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class CharacterDetailFragment : BaseFragment<CharacterDetailState, CharacterDetailViewModel, CharacterDetailFragmentBinding>() {

    /**
     * Base variables
     */

    override val viewModelClass = CharacterDetailViewModel::class.java
    private lateinit var vm: CharacterDetailViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharacterDetailFragmentBinding = CharacterDetailFragmentBinding::inflate
    private val args: CharacterDetailFragmentArgs by navArgs()

    /**
     * Base functions
     */

    override fun setupView(viewModel: CharacterDetailViewModel) {
        vm = viewModel
        vm.requestInformation(args.characterId)
    }

    /**
     * State management
     */

    override fun onNormal(data: CharacterDetailState) {
        data.character?.let{character ->
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
            binding.vpCharacterDetail.adapter = CharacterDetailViewPagerAdapter(this, character){
                findNavController().navigate(CharacterDetailFragmentDirections.actionCharacterDetailFragmentToComicDetailFragment(it))
            }
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

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(dataError: Throwable) {
        when (dataError){
            is NoCharacterException ->{

            }
            else ->{

            }
        }
    }

}