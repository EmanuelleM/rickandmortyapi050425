package com.example.rickandmortywiki.characterdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortywiki.characterdetail.viewmodel.CharacterDetailViewModel
import com.example.rickandmortywiki.characterslist.ui.AppBarTopBack
import com.example.common.network.ResultViewState

@Composable
fun CharacterDetail(
    characterId: String,
    characterDetailViewModel: CharacterDetailViewModel,
    backToHome: () -> Unit,
) {
    characterDetailViewModel.queryCharacterDetail(characterId)
    when (val character = characterDetailViewModel.charactersDetail.collectAsState().value) {
        is ResultViewState.Success -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppBarTopBack("", backToHome)
                AsyncImage(
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp),
                    model = character.data.image,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.padding(vertical = 32.dp),
                    text = character.data.name ?: ""
                )
            }
        }

        is ResultViewState.Error -> {
            Text(modifier = Modifier.padding(vertical = 32.dp), text = "Falha no carregamento")
        }

        else -> {
            Text(modifier = Modifier.padding(vertical = 32.dp), text = "Carregando")
        }
    }

}
