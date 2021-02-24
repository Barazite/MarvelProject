package com.example.marvelproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvelproject.presentation.fragments.characterlist.CharacterListState

abstract class BaseFragment <VS: BaseViewState ,VM : BaseViewModel<VS>, B: ViewDataBinding>: Fragment() {

    /**
     * ViewModel
     */
    private lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>

    /**
     * Data Binding
     */
    //protected, palabra clave para que las clases hijas puedan acceder pero no sobreescribir
    protected lateinit var binding: B

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    /**
     * onCreate
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    /**
     * onResume
     */

    override fun onResume() {
        super.onResume()
        //Get or create the viewModel
        viewModel = ViewModelProvider(this).get(viewModelClass)

        //Setup observers
        viewModel.getObservableState().observe(viewLifecycleOwner, { state ->
            onNormal(state.data)
            when(state){
                is BaseState.Error ->{
                    onError(state.dataError)
                }
                is BaseState.Loading ->{
                    onLoading(state.dataLoading)
                }
                else -> {}
            }
        })

        //Setup view sending View model
        setupView(viewModel)

        //Fragment start
        viewModel.onStart()
    }


    /**
     * Setup view when the viewModel exists
     */
    abstract fun setupView(viewModel: VM)

    /**
     * Manage state functions
     */
    abstract fun onNormal(data: VS)
    abstract fun onLoading(dataLoading: BaseExtraData?)
    abstract fun onError(dataError: Throwable)
}