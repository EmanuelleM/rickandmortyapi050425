package com.example.rickandmortywiki.characterdetail.repository

import com.example.common.model.CharacterItem
import com.example.common.network.ResultViewState

interface CharacterDetailRepository {
    suspend fun queryCharacterDetail(characterId: String): ResultViewState<CharacterItem>
}
