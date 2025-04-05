package com.example.rickandmortywiki

import com.example.rickandmortywiki.common.model.CharacterItem

fun characters(): ArrayList<CharacterItem> =
    arrayListOf(
        CharacterItem(
            id = "1",
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        ),
        CharacterItem(
            id = "8",
            name = "Adjudicator Rick",
            species = "Human",
            status = "Dead",
            image = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
        ),
    )
