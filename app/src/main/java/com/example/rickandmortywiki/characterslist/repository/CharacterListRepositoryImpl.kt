package com.example.rickandmortywiki.characterslist.repository

import com.example.rickandmortywiki.characterslist.datasource.CharacterListDataSource
import com.example.common.model.CharacterList
import com.example.common.network.ResponseResult
import com.example.common.network.ResultMapper
import com.example.common.network.ResultViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterListRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterListDataSource: CharacterListDataSource,
) : CharacterListRepository {

    override suspend fun getCharactersList(): ResultViewState<CharacterList> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                characterListDataSource.getCharactersList(),
            )
        }
    }

    override suspend fun getCharactersListByParameter(
        name: String,
        status: String,
    ): ResultViewState<CharacterList> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                characterListDataSource.getCharactersListByFilter(name, status),
            )
        }
    }
}
