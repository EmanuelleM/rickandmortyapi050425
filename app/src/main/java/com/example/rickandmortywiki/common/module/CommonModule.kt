package com.example.rickandmortywiki.common.module

import com.example.rickandmortywiki.common.network.NetworkUtils
import com.example.rickandmortywiki.common.network.RickAndMortyApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val webService =
    DI.Module("webService") {
        bind<RickAndMortyApi>() with singleton { RickAndMortyApi() }
    }

val networkUtils =
    DI.Module("networkUtils") {
        bind<NetworkUtils>() with singleton { NetworkUtils }
    }
