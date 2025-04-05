package com.example.rickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortywiki.characterdetail.ui.CharacterDetail
import com.example.rickandmortywiki.characterdetail.viewmodel.CharacterDetailViewModel
import com.example.rickandmortywiki.characterslist.ui.CharactersList
import com.example.rickandmortywiki.characterslist.ui.SearchCharacter
import com.example.rickandmortywiki.characterslist.viewmodel.CharacterListViewModel
import com.example.common.network.ResultViewState
import com.example.rickandmortywiki.navigation.NavigationArguments
import com.example.rickandmortywiki.navigation.NavigationDestinations
import com.example.rickandmortywiki.theme.RickAndMortyWikiTheme
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DIContext
import org.kodein.di.diContext
import org.kodein.di.instance

class MainActivity : ComponentActivity(), DIAware {

    override lateinit var di: DI
    override val diContext: DIContext<*> = diContext(this)

    private val characterListViewModel: CharacterListViewModel by instance()
    private val characterDetailViewModel: CharacterDetailViewModel by instance()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di = (applicationContext as DIAware).di

        setContent {
            RickAndMortyWikiTheme {
                Scaffold { paddingValues ->
                    Box(Modifier.padding(paddingValues)) {
                        MainNavHost(characterListViewModel, characterDetailViewModel)
                    }
                }
            }
        }
    }

    @Composable
    private fun MainNavHost(
        characterListViewModel: CharacterListViewModel,
        characterDetailViewModel: CharacterDetailViewModel,
    ) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = NavigationDestinations.CHARACTER_LIST) {
            composable(route = NavigationDestinations.CHARACTER_LIST) {
                when (
                    val characterState =
                        characterListViewModel.charactersList.collectAsState().value
                ) {
                    is ResultViewState.Loading -> Text(stringResource(R.string.carregando_api))
                    is ResultViewState.Success -> {
                        CharactersList(
                            characterItem = characterState.data.results,
                            onSearchClick = {
                                navController.navigate(NavigationDestinations.CHARACTER_SEARCH)
                            },
                            onDetailClick = { id ->
                                navController.navigate("${NavigationDestinations.CHARACTER_DETAIL}/$id")
                            },
                        )
                    }

                    is ResultViewState.Error -> Text(text = characterState.resultError)
                }
            }

            composable(route = NavigationDestinations.CHARACTER_SEARCH) {
                SearchCharacter(
                    backToHome = {
                        navController.popBackStack()
                    },
                    characterId = { name, status -> searchById(name, status) },
                )
            }

            composable(route = "${NavigationDestinations.CHARACTER_DETAIL}/{${NavigationArguments.CHARACTER_ID}}") { navBackStackEntry ->
                navBackStackEntry.arguments?.getString(NavigationArguments.CHARACTER_ID)?.let {
                    CharacterDetail(
                        characterId = it,
                        characterDetailViewModel,
                    ) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    private fun searchById(
        name: String,
        status: String,
    ) {
        characterListViewModel.queryCharactersListByParameter(name, status)
    }
}
