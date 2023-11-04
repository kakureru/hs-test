package com.hammersystemstest.menu.di

import com.hammersystemstest.menu.data.MenuRepositoryImpl
import com.hammersystemstest.menu.data.db.MenuDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val dataModule = module {

    single {
        MenuRepositoryImpl(
            theMealDbApi = get(),
            menuDao = get(),
        )
    }

    single {
        MenuDatabase.getInstance(application = androidApplication()).menuDao()
    }
}