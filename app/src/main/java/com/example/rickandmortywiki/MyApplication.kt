package com.example.rickandmortywiki

import android.app.Application
import com.example.rickandmortywiki.characterdetail.module.characterDetailDataSource
import com.example.rickandmortywiki.characterdetail.module.characterDetailRepository
import com.example.rickandmortywiki.characterdetail.module.characterDetailViewModel
import com.example.rickandmortywiki.characterslist.module.charactersDataSource
import com.example.rickandmortywiki.characterslist.module.charactersListRepository
import com.example.rickandmortywiki.characterslist.module.charactersUseCase
import com.example.rickandmortywiki.characterslist.module.charactersViewModel
import com.example.rickandmortywiki.common.module.webService
import org.kodein.di.DI
import org.kodein.di.DIAware

class MyApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(webService)
        import(charactersDataSource)
        import(charactersListRepository)
        import(charactersUseCase)
        import(charactersViewModel)
        import(characterDetailDataSource)
        import(characterDetailRepository)
        import(characterDetailViewModel)
    }
}
