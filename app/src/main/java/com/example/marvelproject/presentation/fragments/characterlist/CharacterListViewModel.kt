package com.example.marvelproject.presentation.fragments.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {

    private val state = MutableLiveData<BaseState>()

    fun getState(): LiveData<BaseState> = state


    fun requestInformation(limit: Int = 20) {
        state.postValue(BaseState.Loading())
        viewModelScope.launch {
            try{
                val response = MarvelRepository().getAllCharacters(limit)
                state.postValue(BaseState.Normal(CharacterListState(response)))
            }catch (e: Exception){
                state.postValue(BaseState.Error(e))
            }
        }
    }

    fun onActionChangeSpinnerValue(limit: String) {
        requestInformation(limit.toInt())
    }
}