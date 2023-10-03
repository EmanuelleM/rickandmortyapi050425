package com.example.rickandmortywiki.characterdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import coil.compose.AsyncImage
import com.example.rickandmortywiki.characterdetail.viewmodel.CharacterDetailViewModel
import com.example.rickandmortywiki.characterslist.ui.AppBarTopBack

@Composable
fun CharacterDetail(
    characterId: String,
    characterDetailViewModel: CharacterDetailViewModel,
    backToHome: () -> Unit,
) {
    characterDetailViewModel.queryCharacterDetail(characterId)
    val character = characterDetailViewModel.charactersDetail.collectAsState().value.characters
    Column {
        AppBarTopBack("", backToHome)
        AsyncImage(model = character.image, contentDescription = null)
        Text(text = character.name ?: "")
    }
}
