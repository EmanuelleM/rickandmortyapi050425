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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortywiki.R
import com.example.rickandmortywiki.theme.Purple40
import com.example.rickandmortywiki.theme.PurpleGrey40

const val VIVO = "Vivo"
const val MORTO = "Morto"
const val DESCONHECIDO = "Desconhecido"

@Composable
fun SearchCharacter(
    backToHome: () -> Unit,
    characterId: (name: String, status: String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        AppBarTopBack(stringResource(R.string.filtro), backToHome)
        Column {
            var characterName = ""
            var selectedState by remember { mutableStateOf(characterName) }

            Text(text = stringResource(R.string.nome))

            SimpleFilledTextFieldSample { characterName = it }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.status))

                Row {
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = VIVO
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == VIVO) Purple40 else PurpleGrey40),
                    )
                    {
                        Text(text = stringResource(R.string.vivo))
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = MORTO
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == MORTO) Purple40 else PurpleGrey40),

                        ) {
                        Text(text = stringResource(R.string.morto))
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        onClick = {
                            selectedState = DESCONHECIDO
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedState == DESCONHECIDO) Purple40 else PurpleGrey40),
                    ) {

                        Text(text = stringResource(R.string.deconhecido))
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
                    Text(text = stringResource(R.string.filtrar))
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
        modifier = Modifier.testTag(stringResource(R.string.character_name)),
        value = characterName,
        onValueChange = {
            characterName = it
            name(characterName)
        },
    )
}
