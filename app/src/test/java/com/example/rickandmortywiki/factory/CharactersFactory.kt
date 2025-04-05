package com.example.rickandmortywiki.factory

import com.example.common.model.CharacterItem
import com.example.common.model.CharacterList

internal object CharactersFactory {
    fun getCharacters() =
        CharacterList(
            results =
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
            ),
        )
}
