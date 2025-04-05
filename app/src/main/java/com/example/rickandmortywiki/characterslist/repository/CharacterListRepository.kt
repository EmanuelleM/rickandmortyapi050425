package com.example.rickandmortywiki.characterslist.repository

import com.example.common.model.CharacterList
import com.example.common.network.ResultViewState

interface CharacterListRepository {
    suspend fun getCharactersList(): ResultViewState<CharacterList>

    suspend fun getCharactersListByParameter(
        name: String,
        status: String,
    ): ResultViewState<CharacterList>
}
