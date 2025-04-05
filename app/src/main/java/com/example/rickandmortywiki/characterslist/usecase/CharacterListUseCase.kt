package com.example.rickandmortywiki.characterslist.usecase

import com.example.rickandmortywiki.characterslist.repository.CharacterListRepository
import com.example.common.model.CharacterList
import com.example.common.network.ResultViewState

class CharacterListUseCase(
    private val repository: CharacterListRepository,
) {
    suspend fun getCharacters(): ResultViewState<CharacterList> {
        return when (val characters = repository.getCharactersList()) {
            is ResultViewState.Success -> {
                successFlow(characters.data)
            }

            is ResultViewState.Error -> {
                ResultViewState.Error(characters.resultError)
            }

            is ResultViewState.Loading -> {
                ResultViewState.Loading
            }
        }
    }

    suspend fun getCharactersByFilter(
        name: String,
        status: String,
    ): ResultViewState<CharacterList> {
        return when (val characters = repository.getCharactersListByParameter(name, status)) {
            is ResultViewState.Success -> {
                successFlow(characters.data)
            }

            is ResultViewState.Loading -> {
                ResultViewState.Loading
            }

            is ResultViewState.Error -> {
                ResultViewState.Error(characters.resultError)
            }
        }
    }

    private fun successFlow(characters: CharacterList) =
        if (characters.results.isEmpty()) {
            ResultViewState.Error("Empty List")
        } else {
            ResultViewState.Success(characters)
        }
}
