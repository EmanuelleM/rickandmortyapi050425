package com.example.rickandmortywiki.characterslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywiki.characterslist.usecase.CharactersUseCase
import com.example.rickandmortywiki.common.model.Characters
import com.example.rickandmortywiki.common.networking.ResultViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val useCase: CharactersUseCase,
) : ViewModel() {
    private val _charactersList =
        MutableStateFlow<ResultViewState<Characters>>(
            ResultViewState.Initial,
        )

    val charactersList = _charactersList.asStateFlow()

    fun getCharactersList() {
        _charactersList.tryEmit(ResultViewState.Loading)
        viewModelScope.launch {
            when (val characters = useCase.getCharacters()) {
                is ResultViewState.Success -> {
                    _charactersList.emit(characters)
                }

                else -> {
                    if (characters is ResultViewState.Error) {
                        val charactersError = characters.resultError
                        _charactersList.emit(ResultViewState.Error(charactersError))
                    }
                }
            }
        }
    }

    fun queryCharactersListByParameter(
        name: String,
        status: String,
    ) = viewModelScope.launch {
        _charactersList.tryEmit(ResultViewState.Loading)
        viewModelScope.launch {
            when (val characters = useCase.getCharactersByFilter(name, status)) {
                is ResultViewState.Success -> {
                    _charactersList.emit(characters)
                }

                else -> {
                    if (characters is ResultViewState.Error) {
                        val charactersError = characters.resultError
                        _charactersList.emit(ResultViewState.Error(charactersError))
                    }
                }
            }
        }
    }
}
