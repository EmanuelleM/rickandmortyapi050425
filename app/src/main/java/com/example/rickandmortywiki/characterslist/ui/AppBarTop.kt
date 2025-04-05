package com.example.rickandmortywiki.characterslist.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortywiki.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTop(
    title: String,
    backToHome: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = backToHome) {
                Icon(Icons.Filled.Search, stringResource(R.string.pesquisa_personagem))
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTopBack(
    title: String,
    navigateToList: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = navigateToList) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.pesquisa_personagem))
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopAppBarPreview(){
    TopAppBar(
        title = {Text(text = stringResource(R.string.app_name))},
        navigationIcon = {}
    )
}