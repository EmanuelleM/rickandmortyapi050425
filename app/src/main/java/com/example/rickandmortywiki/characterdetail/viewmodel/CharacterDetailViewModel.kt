package com.example.rickandmortywiki.characterdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywiki.characterdetail.repository.CharacterDetailRepository
import com.example.rickandmortywiki.common.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: CharacterDetailRepository,
) : ViewModel() {
    private val _charactersDetail = MutableStateFlow(ViewStatus.Success(Character()))

    val charactersDetail = _charactersDetail.asStateFlow()

    fun queryCharacterDetail(characterDetail: String) =
        viewModelScope.launch {
            val response = repository.queryCharacterDetail(characterDetail)
            response.collect { results ->
                _charactersDetail.value = ViewStatus.Success(results)
            }
        }
}

sealed class ViewStatus {
    data class Success(val characters: Character) : ViewStatus()

    data class Error(val exception: Throwable) : ViewStatus()
}
