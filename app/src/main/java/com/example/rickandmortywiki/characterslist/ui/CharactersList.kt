package com.example.rickandmortywiki.characterslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortywiki.common.model.Character

@Composable
fun CharactersList(
    characterItem: ArrayList<Character>,
    onSearchClick: () -> Unit,
    onDetailClick: (id: String) -> Unit,
) {
    Column {
        AppBarTop("Personagens", onSearchClick)
        Row {
            Column {
                CharacterGrid(characters = characterItem, onDetailClick)
            }
        }
    }
}

@Composable
fun CharacterGrid(
    characters: ArrayList<Character>,
    onDetailClick: (id: String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(characters.size) { item ->
            CharacterItem(character = characters[item], onDetailClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    character: Character,
    onDetailClick: (id: String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        onClick = {
            character.id?.let { id ->
                onDetailClick(id)
            }
        },
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        character.name?.let { Text(text = it) }
    }
}
