package com.example.rickandmortywiki.characterslist.datasource

import com.example.rickandmortywiki.common.model.CharacterList
import com.example.rickandmortywiki.common.network.ResponseResult

interface CharactersDataSource {
    suspend fun getCharactersList(): ResponseResult<CharacterList>

    suspend fun getCharactersListByFilter(
        name: String,
        status: String,
    ): ResponseResult<CharacterList>
}
