package com.example.rickandmortywiki.characterslist.repository

import com.example.rickandmortywiki.characterslist.datasource.CharactersDataSource
import com.example.rickandmortywiki.common.model.Characters
import com.example.rickandmortywiki.common.network.ResultMapper
import com.example.rickandmortywiki.common.network.ResultViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {
    override suspend fun getCharactersList(): ResultViewState<Characters> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                charactersDataSource.getCharactersList(),
            )
        }
    }

    override suspend fun getCharactersListByParameter(
        name: String,
        status: String,
    ): ResultViewState<Characters> {
        return withContext(ioDispatcher) {
            ResultMapper.toResultViewState(
                charactersDataSource.getCharactersListByFilter(name, status),
            )
        }
    }
}
