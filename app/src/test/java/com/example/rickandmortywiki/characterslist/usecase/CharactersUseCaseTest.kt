package com.example.rickandmortywiki.characterslist.usecase


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmortywiki.characterslist.repository.CharactersRepository
import com.example.rickandmortywiki.common.network.ResultViewState
import com.example.rickandmortywiki.factory.CharactersFactory
import com.example.rickandmortywiki.rule.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersUseCaseTest {
    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<CharactersRepository>()
    private val useCase by lazy {
        CharactersUseCase(repository)
    }

    @Test
    fun `Should return characters list`() =
        runTest {
            // given
            val resultViewState =
                ResultViewState.Success(
                    CharactersFactory.getCharacters(),
                )
            // when
            coEvery { repository.getCharactersList() } returns resultViewState
            val result = useCase.getCharacters()
            coVerify { repository.getCharactersList() }
            // then
            assertEquals(2, (result as ResultViewState.Success).data.results.size)
            assertEquals("Rick Sanchez", result.data.results[0].name)
        }

    @Test
    fun `Should return error when not exists characters`() =
        runTest {
            // given
            val resultError = ResultViewState.Error("generic error")

            // when
            coEvery { repository.getCharactersList() } returns resultError

            val result = useCase.getCharacters()
            coVerify { repository.getCharactersList() }

            // then
            assertEquals(resultError.resultError, (result as ResultViewState.Error).resultError)
        }
}
