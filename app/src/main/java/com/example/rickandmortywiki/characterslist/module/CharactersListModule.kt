package com.example.rickandmortywiki.characterslist.module

import com.example.rickandmortywiki.characterslist.datasource.CharactersDataSource
import com.example.rickandmortywiki.characterslist.datasource.CharactersDataSourceImpl
import com.example.rickandmortywiki.characterslist.repository.CharactersRepository
import com.example.rickandmortywiki.characterslist.repository.CharactersRepositoryImpl
import com.example.rickandmortywiki.characterslist.usecase.CharactersUseCase
import com.example.rickandmortywiki.characterslist.viewmodel.CharactersViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val charactersDataSource =
    DI.Module("charactersDataSource") {
        bind<CharactersDataSource>() with provider { CharactersDataSourceImpl(instance()) }
    }

val charactersListRepository =
    DI.Module("charactersListRepository") {
        bind<CharactersRepository>() with
            provider {
                CharactersRepositoryImpl(
                    charactersDataSource = instance(),
                )
            }
    }

val charactersUseCase =
    DI.Module("charactersUseCase") {
        bind<CharactersUseCase>() with provider { CharactersUseCase(instance()) }
    }

val charactersViewModel =
    DI.Module("charactersViewModel") {
        bind<CharactersViewModel>() with provider { CharactersViewModel(instance()) }
    }
