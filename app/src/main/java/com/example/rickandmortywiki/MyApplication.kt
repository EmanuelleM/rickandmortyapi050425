package com.example.rickandmortywiki

import android.app.Application
import com.example.rickandmortywiki.characterdetail.module.characterDetailDataSource
import com.example.rickandmortywiki.characterdetail.module.characterDetailRepository
import com.example.rickandmortywiki.characterdetail.module.characterDetailViewModel
import com.example.rickandmortywiki.characterslist.module.characterListDataSource
import com.example.rickandmortywiki.characterslist.module.charactersListRepository
import com.example.rickandmortywiki.characterslist.module.characterListUseCase
import com.example.rickandmortywiki.characterslist.module.characterListViewModel
import com.example.common.module.networkUtils
import com.example.common.module.webService
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class MyApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@MyApplication))
        import(webService)
        import(characterListDataSource)
        import(charactersListRepository)
        import(characterListUseCase)
        import(characterListViewModel)
        import(characterDetailDataSource)
        import(characterDetailRepository)
        import(characterDetailViewModel)
        import(networkUtils)
    }
}
