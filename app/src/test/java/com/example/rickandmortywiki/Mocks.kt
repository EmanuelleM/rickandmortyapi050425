package com.example.rickandmortywiki

import com.example.rickandmortywiki.common.model.Character

fun characters(): ArrayList<Character> =
    arrayListOf(
        Character(
            id = "1",
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        ),
        Character(
            id = "8",
            name = "Adjudicator Rick",
            species = "Human",
            status = "Dead",
            image = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
        ),
    )
