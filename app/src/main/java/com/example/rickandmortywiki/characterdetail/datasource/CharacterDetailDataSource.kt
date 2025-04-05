package com.example.rickandmortywiki.characterdetail.datasource

import com.example.common.model.CharacterItem
import com.example.common.network.ResponseResult

interface CharacterDetailDataSource {
    suspend fun getCharacterDetail(characterId: String): ResponseResult<CharacterItem>
}
