package com.example.rickandmortywiki.characterslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun SearchCharacter(
    backToHome: () -> Unit,
    characterId: (name: String, status: String) -> Unit,
) {
    Column {
        AppBarTopBack("Filtro", backToHome)
        Column {
            var characterName = ""
            var selectedState = "alive"

            Text(text = "Nome")

            SimpleFilledTextFieldSample { characterName = it }

            Column {
                Text(text = "Status")

                Row {
                    Button(onClick = { selectedState = "alive" }) {
                        Text(text = "alive")
                    }
                    Button(onClick = { selectedState = "dead" }) {
                        Text(text = "dead")
                    }
                    Button(onClick = { selectedState = "unknow" }) {
                        Text(text = "unknow")
                    }
                }
                Spacer(modifier = Modifier.padding(40.dp))
                Button(
                    onClick = {
                        characterId(characterName, selectedState)
                        backToHome()
                    },
                ) {
                    Text(text = "Filtrar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFilledTextFieldSample(name: (String) -> Unit) {
    var characterName by rememberSaveable { mutableStateOf("") }

    TextField(
        modifier = Modifier.testTag("character_name"),
        value = characterName,
        onValueChange = {
            characterName = it
            name(characterName)
        },
    )
}
