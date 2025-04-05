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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.model.CharacterItem
import com.example.rickandmortywiki.R

@Composable
fun CharactersList(
    characterItem: ArrayList<CharacterItem>,
    onSearchClick: () -> Unit,
    onDetailClick: (id: String) -> Unit,
) {
    Column {
        AppBarTop(stringResource(R.string.personagens), onSearchClick)
        Row {
            Column {
                CharacterGrid(characterItems = characterItem, onDetailClick)
            }
        }
    }
}

@Composable
fun CharacterGrid(
    characterItems: ArrayList<CharacterItem>,
    onDetailClick: (id: String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(characterItems.size) { item ->
            CharacterItem(characterItem = characterItems[item], onDetailClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(
    characterItem: CharacterItem,
    onDetailClick: (id: String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        onClick = {
            characterItem.id?.let { id ->
                onDetailClick(id)
            }
        },
    ) {
        AsyncImage(
            model = characterItem.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        characterItem.name?.let { Text(text = it) }
    }
}
