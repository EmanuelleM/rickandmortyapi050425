package com.example.rickandmortywiki.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    var name: String? = null,
    var url: String? = null,
)
