package com.example.rickandmortywiki.characterslist.repository

import com.example.rickandmortywiki.characterslist.datasource.CharacterListDataSource
import com.example.common.network.ResponseResult
import com.example.common.network.ResultMapper
import com.example.rickandmortywiki.factory.CharactersFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryImplTest {
    private val dataSource = mockk<CharacterListDataSource>()
    private val repository by lazy {
        CharacterListRepositoryImpl(
            characterListDataSource = dataSource,
        )
    }

    @Test
    fun `Should return characters list when request is success`() =
        runTest {
            // given
            val responseSuccess =
                ResponseResult.Success(
                    CharactersFactory.getCharacters(),
                )
            // when
            coEvery { dataSource.getCharactersList() } returns responseSuccess

            val result = repository.getCharactersList()

            coVerify { dataSource.getCharactersList() }
            // then
            Assert.assertEquals(ResultMapper.toResultViewState(responseSuccess), result)
        }

    @Test
    fun `Should return error when request is fail`() =
        runTest {
            // given
            val responseError = ResponseResult.Error("generic error")

            // when
            coEvery { dataSource.getCharactersList() } returns responseError

            val result = repository.getCharactersList()

            coVerify { dataSource.getCharactersList() }

            // then
            Assert.assertEquals(ResultMapper.toResultViewState(responseError), result)
        }
}
