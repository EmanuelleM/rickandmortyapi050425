package com.example.rickandmortywiki.common.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache

class RickAndMortyApi {
    private val size = 10 * 1024 * 1024

    private val cacheFactory = MemoryCacheFactory(maxSizeBytes = size)

    private val apolloClient =
        ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .normalizedCache(cacheFactory)
            .fetchPolicy(FetchPolicy.NetworkFirst)
            .build()

    fun getApolloClient(): ApolloClient = apolloClient
}
