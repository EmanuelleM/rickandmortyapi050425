package com.example.rickandmortywiki.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmortywiki.characterslist.ui.CharactersList
import com.example.common.model.CharacterItem
import com.example.rickandmortywiki.theme.RickAndMortyWikiTheme
import org.junit.Rule
import org.junit.Test

class CharactersListKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun characterList() {
            composeTestRule.setContent {
                RickAndMortyWikiTheme {
                    CharactersList(
                        characterItem = characters(),
                        onSearchClick = {},
                        onDetailClick = {},
                    )
                }
            }

            composeTestRule.onNodeWithContentDescription("pesquisa personagem").performClick()
            composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed().performClick()
            composeTestRule.onNodeWithText("Adjudicator Rick").assertIsDisplayed().performClick()
        }

    private fun characters(): ArrayList<CharacterItem> =
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
}
