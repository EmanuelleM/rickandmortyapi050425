package com.example.rickandmortywiki.characterdetail.repository

import com.example.rickandmortywiki.characterdetail.datasource.CharacterDetailDataSource
import com.example.rickandmortywiki.common.model.Character
import com.example.rickandmortywiki.common.network.ResultMapper
import com.example.rickandmortywiki.common.network.ResultViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDetailRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterDataSource: CharacterDetailDataSource,
) : CharacterDetailRepository {
    override suspend fun queryCharacterDetail(characterId: String): ResultViewState<Character> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                characterDataSource.getCharacterDetail(characterId),
            )
        }

//        return characterDataSource.getCharacterDetail(characterId).map { result ->
//            val character = result.character
//            return@map Character(
//                id = character?.id,
//                name = character?.name,
//                status = character?.status,
//                species = character?.species,
//                type = character?.species,
//                gender = character?.gender,
//                image = character?.image,
//                created = character?.created,
//            )
//        }
    }
}
