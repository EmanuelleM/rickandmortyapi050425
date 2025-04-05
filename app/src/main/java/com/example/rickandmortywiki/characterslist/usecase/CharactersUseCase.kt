package com.example.rickandmortywiki.characterslist.usecase

import com.example.rickandmortywiki.characterslist.repository.CharactersRepository
import com.example.rickandmortywiki.common.model.CharacterList
import com.example.rickandmortywiki.common.network.ResultViewState

class CharactersUseCase(
    private val repository: CharactersRepository,
) {
    suspend fun getCharacters(): ResultViewState<CharacterList> {
        return when (val characters = repository.getCharactersList()) {
            is ResultViewState.Success -> {
                successFlow(characters.data)
            }

            else -> ResultViewState.Error("generic error")
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

            is ResultViewState.Error -> ResultViewState.Error("generic error")
        }
    }

    private fun successFlow(characters: CharacterList) =
        if (characters.results.isEmpty()) {
            ResultViewState.Error("empty list")
        } else {
            ResultViewState.Success(characters)
        }
}
