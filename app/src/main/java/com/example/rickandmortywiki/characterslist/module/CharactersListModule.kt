package com.example.rickandmortywiki.characterslist.module

import com.example.rickandmortywiki.characterslist.datasource.CharacterListDataSource
import com.example.rickandmortywiki.characterslist.datasource.CharacterListDataSourceImpl
import com.example.rickandmortywiki.characterslist.repository.CharacterListRepository
import com.example.rickandmortywiki.characterslist.repository.CharacterListRepositoryImpl
import com.example.rickandmortywiki.characterslist.usecase.CharacterListUseCase
import com.example.rickandmortywiki.characterslist.viewmodel.CharacterListViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val characterListDataSource =
    DI.Module("charactersDataSource") {
        bind<CharacterListDataSource>() with provider { CharacterListDataSourceImpl(instance()) }
    }

val charactersListRepository =
    DI.Module("charactersListRepository") {
        bind<CharacterListRepository>() with
            provider {
                CharacterListRepositoryImpl(
                    characterListDataSource = instance(),
                )
            }
    }

val characterListUseCase =
    DI.Module("charactersUseCase") {
        bind<CharacterListUseCase>() with provider { CharacterListUseCase(instance()) }
    }

val characterListViewModel =
    DI.Module("charactersViewModel") {
        bind<CharacterListViewModel>() with provider { CharacterListViewModel(instance(), instance(), instance()) }
    }
