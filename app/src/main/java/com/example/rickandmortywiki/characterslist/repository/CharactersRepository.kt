package com.example.rickandmortywiki.characterslist.repository

import com.example.rickandmortywiki.common.model.CharacterList
import com.example.rickandmortywiki.common.network.ResultViewState

interface CharactersRepository {
    suspend fun getCharactersList(): ResultViewState<CharacterList>

    suspend fun getCharactersListByParameter(
        name: String,
        status: String,
    ): ResultViewState<CharacterList>
}
