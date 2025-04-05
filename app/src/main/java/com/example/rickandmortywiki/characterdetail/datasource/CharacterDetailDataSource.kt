package com.example.rickandmortywiki.characterdetail.datasource

import com.example.rickandmortywiki.common.model.CharacterItem
import com.example.rickandmortywiki.common.network.ResponseResult

interface CharacterDetailDataSource {
    suspend fun getCharacterDetail(characterId: String): ResponseResult<CharacterItem>
}
