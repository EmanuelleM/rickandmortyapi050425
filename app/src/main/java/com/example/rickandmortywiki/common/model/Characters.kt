package com.example.rickandmortywiki.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    var results: ArrayList<Character> = arrayListOf(),
)
