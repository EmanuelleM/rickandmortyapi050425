package com.example.rickandmortywiki.characterdetail.datasource

import com.example.rickandmortywiki.common.model.Character
import com.example.rickandmortywiki.common.networking.ResponseResult

interface CharacterDetailDataSource {
    suspend fun getCharacterDetail(characterId: String): ResponseResult<Character>
}
