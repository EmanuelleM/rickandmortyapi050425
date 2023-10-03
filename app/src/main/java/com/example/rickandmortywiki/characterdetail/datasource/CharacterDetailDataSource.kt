package com.example.rickandmortywiki.characterdetail.datasource

import com.example.rickandmortywiki.CharacterQuery
import kotlinx.coroutines.flow.Flow

interface CharacterDetailDataSource {
    fun getCharacterDetail(characterId: String): Flow<CharacterQuery.Data>
}
