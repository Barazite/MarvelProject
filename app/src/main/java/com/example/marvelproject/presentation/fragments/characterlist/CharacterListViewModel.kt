package com.example.marvelproject.presentation.fragments.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.base.BaseViewModel
import com.example.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterListViewModel: BaseViewModel<CharacterListState>() {

    override val defaultState: CharacterListState = CharacterListState()

    override fun onStartFirstTime() {
        requestInformation()
    }

    private fun requestInformation() {
        updateToLoadingState()
        checkDataState { state ->
            executeCoroutines({
                val response = MarvelRepository().getAllCharacters(state.limit)
                updateToNormalState(state.copy(characterList = response))
            }, { error ->
                updateToErrorState(error)
            })
        }
    }

    fun onActionChangeSpinnerValue(limit: String) {
        checkDataState { state ->
            if(state.limit != limit.toInt()){
                updateDataState(state.copy(limit = limit.toInt()))
                requestInformation()
            }
        }
    }
}