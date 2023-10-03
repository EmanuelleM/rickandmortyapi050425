package com.example.rickandmortywiki.characterslist.repository

import com.example.rickandmortywiki.common.model.Characters
import com.example.rickandmortywiki.common.networking.ResultViewState

interface CharactersRepository {
    suspend fun getCharactersList(): ResultViewState<Characters>

    suspend fun getCharactersListByParameter(
        name: String,
        status: String,
    ): ResultViewState<Characters>
}
