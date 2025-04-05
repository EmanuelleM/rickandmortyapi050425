package com.example.rickandmortywiki.characterdetail.repository

import com.example.rickandmortywiki.characterdetail.datasource.CharacterDetailDataSource
import com.example.common.model.CharacterItem
import com.example.common.network.ResultMapper
import com.example.common.network.ResultViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDetailRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterDataSource: CharacterDetailDataSource,
) : CharacterDetailRepository {
    override suspend fun queryCharacterDetail(characterId: String): ResultViewState<CharacterItem> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                characterDataSource.getCharacterDetail(characterId),
            )
        }
    }
}
