package com.example.rickandmortywiki.characterslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.rickandmortywiki.ui.theme.Purple40
import com.example.rickandmortywiki.ui.theme.PurpleGrey40

@Composable
fun SearchCharacter(
    backToHome: () -> Unit,
    characterId: (name: String, status: String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        AppBarTopBack("Filtro", backToHome)
        Column {
            var characterName = ""
            var selectedState by remember { mutableStateOf(characterName) }

            Text(text = "Nome")

            SimpleFilledTextFieldSample { characterName = it }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Status")

                Row {
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = "alive"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == "alive") Purple40 else PurpleGrey40),
                    )
                    {
                        Text(text = "alive")
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = "dead"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == "dead") Purple40 else PurpleGrey40),

                        ) {
                        Text(text = "dead")
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = "unknow"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == "unknow") Purple40 else PurpleGrey40),
                    ) {

                        Text(text = "unknow")
                    }
                }

                Spacer(modifier = Modifier.padding(40.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
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
