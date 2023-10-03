package com.example.rickandmortywiki.characterdetail.datasource

import com.example.rickandmortywiki.CharacterQuery
import com.example.rickandmortywiki.common.networking.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDetailDataSourceImpl(
    private val webService: RickAndMortyApi,
) : CharacterDetailDataSource {
    override fun getCharacterDetail(characterId: String): Flow<CharacterQuery.Data> =
        flow {
            webService.getApolloClient().query(CharacterQuery(characterId)).execute().data?.let { emit(it) }
        }
}
