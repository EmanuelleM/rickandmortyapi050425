package com.example.rickandmortywiki.factory

import com.example.rickandmortywiki.common.model.Character
import com.example.rickandmortywiki.common.model.Characters

internal object CharactersFactory {
    fun getCharacters() =
        Characters(
            results =
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
            ),
        )
}
