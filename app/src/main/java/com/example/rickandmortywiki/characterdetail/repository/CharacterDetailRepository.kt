package com.example.rickandmortywiki.characterdetail.repository

import com.example.rickandmortywiki.common.model.Character
import com.example.rickandmortywiki.common.network.ResultViewState

interface CharacterDetailRepository {
    suspend fun queryCharacterDetail(characterId: String): ResultViewState<Character>
}
