package com.example.common.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterList(
    var results: ArrayList<CharacterItem> = arrayListOf(),
)
