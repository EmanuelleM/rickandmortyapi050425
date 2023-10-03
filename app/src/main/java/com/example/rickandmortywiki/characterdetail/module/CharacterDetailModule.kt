package com.example.rickandmortywiki.characterdetail.module

import com.example.rickandmortywiki.characterdetail.datasource.CharacterDetailDataSource
import com.example.rickandmortywiki.characterdetail.datasource.CharacterDetailDataSourceImpl
import com.example.rickandmortywiki.characterdetail.repository.CharacterDetailRepository
import com.example.rickandmortywiki.characterdetail.repository.CharacterDetailRepositoryImpl
import com.example.rickandmortywiki.characterdetail.viewmodel.CharacterDetailViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val characterDetailDataSource =
    DI.Module("characterDetailDataSource") {
        bind<CharacterDetailDataSource>() with provider { CharacterDetailDataSourceImpl(instance()) }
    }

val characterDetailRepository =
    DI.Module("characterDetailRepository") {
        bind<CharacterDetailRepository>() with provider { CharacterDetailRepositoryImpl(instance()) }
    }

val characterDetailViewModel =
    DI.Module("characterDetailViewModel") {
        bind<CharacterDetailViewModel>() with provider { CharacterDetailViewModel(instance()) }
    }
