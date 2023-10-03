package com.example.rickandmortywiki.characterslist.datasource

import com.example.rickandmortywiki.common.model.Characters
import com.example.rickandmortywiki.common.networking.ResponseResult

interface CharactersDataSource {
    suspend fun getCharactersList(): ResponseResult<Characters>

    suspend fun getCharactersListByFilter(
        name: String,
        status: String,
    ): ResponseResult<Characters>
}
