package com.example.rickandmortywiki.characterslist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywiki.characterslist.usecase.CharacterListUseCase
import com.example.common.model.CharacterList
import com.example.common.network.NetworkUtils
import com.example.common.network.ResultViewState
import com.example.common.network.ResultViewState.Success
import com.example.common.network.ResultViewState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase,
    private val networkUtils: NetworkUtils,
    private val appContext: Context,
) : ViewModel() {

    init {
        getCharactersList()
    }

    private val _characterListList = MutableStateFlow<ResultViewState<CharacterList>>(
        ResultViewState.Loading,
        )

    val charactersList = _characterListList.asStateFlow()

    fun getCharactersList() {
        viewModelScope.launch {
            when (val characters = useCase.getCharacters()) {
                is Success -> {
                    _characterListList.emit(characters)
                }

                else -> {
                    if (characters is ResultViewState.Error) {
                        val charactersError = characters
                        charactersError.let { _characterListList.emit(it) }
                    }
                }
            }
        }
    }

    fun queryCharactersListByParameter(
        name: String,
        status: String,
    ) = viewModelScope.launch {
        _characterListList.tryEmit(Loading)
        viewModelScope.launch {
            when (val characterList = useCase.getCharactersByFilter(name, status)) {
                is Success -> {
                    _characterListList.emit(characterList)
                }

                else -> {
                    if (characterList is ResultViewState.Error) {
                        val charactersError = characterList
                        charactersError.let { _characterListList.emit(it) }
                    }
                }
            }
        }
    }

    private fun checkNetworkConnection(context: Context) =
        !(networkUtils.isInternetAvailable(context) && !networkUtils.isWifiConnected(context))
}
