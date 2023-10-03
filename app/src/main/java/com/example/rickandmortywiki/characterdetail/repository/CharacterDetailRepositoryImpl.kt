package com.example.rickandmortywiki.characterdetail.repository

import com.example.rickandmortywiki.characterdetail.datasource.CharacterDetailDataSource
import com.example.rickandmortywiki.common.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterDetailRepositoryImpl(
    private val characterDataSource: CharacterDetailDataSource,
) : CharacterDetailRepository {
    override suspend fun queryCharacterDetail(characterId: String): Flow<Character> {
        return characterDataSource.getCharacterDetail(characterId).map { result ->
            val character = result.character
            return@map Character(
                id = character?.id,
                name = character?.name,
                status = character?.status,
                species = character?.species,
                type = character?.species,
                gender = character?.gender,
                image = character?.image,
                created = character?.created,
            )
        }
    }
}
