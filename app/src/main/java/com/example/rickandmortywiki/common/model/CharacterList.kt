package com.example.rickandmortywiki.common.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterList(
    var results: ArrayList<CharacterItem> = arrayListOf(),
)
