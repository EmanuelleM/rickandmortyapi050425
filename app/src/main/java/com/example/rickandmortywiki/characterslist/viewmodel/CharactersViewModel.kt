package com.example.rickandmortywiki.characterslist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywiki.characterslist.usecase.CharactersUseCase
import com.example.rickandmortywiki.common.model.CharacterList
import com.example.rickandmortywiki.common.network.NetworkUtils
import com.example.rickandmortywiki.common.network.ResultViewState
import com.example.rickandmortywiki.common.network.ResultViewState.Success
import com.example.rickandmortywiki.common.network.ResultViewState.Loading
import com.example.rickandmortywiki.common.network.ResultViewState.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val useCase: CharactersUseCase,
    private val networkUtils: NetworkUtils,
    private val appContext: Context,
) : ViewModel() {

    init {
        getCharactersList()
    }

    private val _characterListList = MutableStateFlow<ResultViewState<CharacterList>>(
        Loading,
        )

    val charactersList = _characterListList.asStateFlow()

    fun getCharactersList() {
        viewModelScope.launch {
            when (val characters = useCase.getCharacters()) {
                is Success -> {
                    _characterListList.emit(characters)
                }

                else -> {
                    if (characters is Error) {
                        val charactersError = characters.resultError
                        _characterListList.emit(Error(charactersError))
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
            when (val characters = useCase.getCharactersByFilter(name, status)) {
                is Success -> {
                    _characterListList.emit(characters)
                }

                else -> {
                    if (characters is Error) {
                        val charactersError = characters.resultError
                        _characterListList.emit(Error(charactersError))
                    }
                }
            }
        }
    }

    private fun checkNetworkConnection(context: Context) =
        !(networkUtils.isInternetAvailable(context) && !networkUtils.isWifiConnected(context))
}
