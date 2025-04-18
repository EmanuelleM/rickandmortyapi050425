package com.example.rickandmortywiki.characterdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywiki.characterdetail.repository.CharacterDetailRepository
import com.example.common.model.CharacterItem
import com.example.common.network.ResultViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: CharacterDetailRepository,
) : ViewModel() {
    private val _charactersDetail = MutableStateFlow<ResultViewState<CharacterItem>>(
        ResultViewState.Loading,
    )

    val charactersDetail = _charactersDetail.asStateFlow()

    fun queryCharacterDetail(characterDetail: String) {
        _charactersDetail.tryEmit(ResultViewState.Loading)
        viewModelScope.launch {
            when (val character = repository.queryCharacterDetail(characterDetail)) {
                is ResultViewState.Success -> {
                    _charactersDetail.emit(character)
                }

                else -> {
                    if (character is ResultViewState.Error) {
                        val charactersError = character.resultError
                        _charactersDetail.emit(ResultViewState.Error(charactersError))
                    }
                }
            }
        }
    }
}

sealed class ViewStatus {
    data class Success(val characters: CharacterItem) : ViewStatus()

    data class Error(val exception: Throwable) : ViewStatus()
}
