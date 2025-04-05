package com.example.rickandmortywiki.characterslist.datasource

import com.example.common.model.CharacterList
import com.example.common.network.ResponseResult

interface CharacterListDataSource {
    suspend fun getCharactersList(): ResponseResult<CharacterList>

    suspend fun getCharactersListByFilter(
        name: String,
        status: String,
    ): ResponseResult<CharacterList>
}
