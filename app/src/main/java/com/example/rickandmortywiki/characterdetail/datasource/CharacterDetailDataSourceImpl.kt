package com.example.rickandmortywiki.characterdetail.datasource

import com.apollographql.apollo3.exception.ApolloException
import com.example.rickandmortywiki.CharacterQuery
import com.example.common.model.CharacterItem
import com.example.common.network.ResponseResult
import com.example.common.network.RickAndMortyApi

class CharacterDetailDataSourceImpl(
    private val webService: RickAndMortyApi,
) : CharacterDetailDataSource {
    override suspend fun getCharacterDetail(characterId: String): ResponseResult<CharacterItem> {

        val serviceResult =
            webService.getApolloClient().query(CharacterQuery(characterId)).execute()
        try {
            val item = serviceResult.data?.character
            return ResponseResult.Success(
                CharacterItem(
                    id = item?.id,
                    name = item?.name,
                    species = item?.species,
                    image = item?.image,
                    status = item?.status,
                )
            )
        } catch (exception: ApolloException) {
            return ResponseResult.Error("$exception")
        }
    }
}

