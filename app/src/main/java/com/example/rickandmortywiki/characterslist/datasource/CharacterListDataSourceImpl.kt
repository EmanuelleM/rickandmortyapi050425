package com.example.rickandmortywiki.characterslist.datasource

import com.apollographql.apollo3.exception.ApolloException
import com.example.rickandmortywiki.CharactersListByFilterQuery
import com.example.rickandmortywiki.CharactersListQuery
import com.example.common.model.CharacterItem
import com.example.common.model.CharacterList
import com.example.common.network.ResponseResult
import com.example.common.network.RickAndMortyApi

class CharacterListDataSourceImpl(
    private val webService: RickAndMortyApi,
) : CharacterListDataSource {
    override suspend fun getCharactersList(): ResponseResult<CharacterList> {
        try {
            val serviceResult = webService.getApolloClient().query(CharactersListQuery()).execute()
            return ResponseResult.Success(
                CharacterList(
                    results =
                        serviceResult.dataAssertNoErrors.characters?.results?.map { item ->
                            CharacterItem(
                                id = item?.id,
                                name = item?.name,
                                species = item?.species,
                                image = item?.image,
                                status = item?.status,
                            )
                        } as ArrayList<CharacterItem>,
                ),
            )
        } catch (exception: ApolloException) {
            return ResponseResult.Error("$exception")
        }
    }

    override suspend fun getCharactersListByFilter(
        name: String,
        status: String,
    ): ResponseResult<CharacterList> {
        val serviceResult =
            webService.getApolloClient().query(CharactersListByFilterQuery(name, status))
                .execute()

        return ResponseResult.Success(
            CharacterList(
                results =
                serviceResult.data?.characters?.results?.map { item ->
                    CharacterItem(
                        id = item?.id,
                        name = item?.name,
                        species = item?.species,
                        image = item?.image,
                        status = item?.status,
                    )
                } as ArrayList<CharacterItem>,
            ),
        )
    }
}
