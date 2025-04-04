package com.example.rickandmortywiki.characterslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmortywiki.characterslist.usecase.CharactersUseCase
import com.example.rickandmortywiki.common.network.ResultViewState
import com.example.rickandmortywiki.factory.CharactersFactory
import com.example.rickandmortywiki.rule.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {
    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val useCase = mockk<CharactersUseCase>()
    private val viewModel by lazy {
        CharactersViewModel(useCase)
    }

    @Test
    fun `should return with characters list when resultViewState is success`() {
        // given
        val resultViewState =
            ResultViewState.Success(
                CharactersFactory.getCharacters(),
            )

        // when
        coEvery { useCase.getCharacters() } returns resultViewState
        viewModel.getCharactersList()
        coVerify { useCase.getCharacters() }

        // then
        assertEquals(resultViewState, viewModel.charactersList.value)
    }

    @Test
    fun `should back error when resultViewState is error`() {
        // given
        val resultViewState = ResultViewState.Error("falha de API")

        // when
        coEvery { useCase.getCharacters() } returns resultViewState
        viewModel.getCharactersList()
        coVerify { useCase.getCharacters() }

        // then
        assertEquals(resultViewState, viewModel.charactersList.value)
    }
}
