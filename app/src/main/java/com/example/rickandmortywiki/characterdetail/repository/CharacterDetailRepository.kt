package com.example.rickandmortywiki.characterdetail.repository

import com.example.rickandmortywiki.common.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    suspend fun queryCharacterDetail(characterId: String): Flow<Character>
}
