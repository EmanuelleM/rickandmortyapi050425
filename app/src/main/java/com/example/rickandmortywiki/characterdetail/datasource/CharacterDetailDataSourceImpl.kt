package com.example.rickandmortywiki.characterdetail.datasource

import com.example.rickandmortywiki.CharacterQuery
import com.example.rickandmortywiki.common.model.Character
import com.example.rickandmortywiki.common.network.ResponseResult
import com.example.rickandmortywiki.common.network.RickAndMortyApi

class CharacterDetailDataSourceImpl(
    private val webService: RickAndMortyApi,
) : CharacterDetailDataSource {
    override suspend fun getCharacterDetail(characterId: String): ResponseResult<Character> {

        val serviceResult =
            webService.getApolloClient().query(CharacterQuery(characterId)).execute()
        val item = serviceResult.data?.character
        return ResponseResult.Success(
            Character(
                id = item?.id,
                name = item?.name,
                species = item?.species,
                image = item?.image,
                status = item?.status,
            )
        )
    }
}

