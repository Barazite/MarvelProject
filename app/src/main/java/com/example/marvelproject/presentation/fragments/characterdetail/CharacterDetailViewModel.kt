package com.example.marvelproject.presentation.fragments.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state


    fun requestInformation(characterId: Int) {
        state.postValue(BaseState.Loading())
        viewModelScope.launch {
            try{
                val response = MarvelRepository().getCharacter(characterId)
                state.postValue(BaseState.Normal(CharacterDetailState(response)))
            }catch (e: Exception){
                state.postValue(BaseState.Error(e))
            }
        }
    }
}