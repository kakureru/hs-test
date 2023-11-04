package com.hammersystemstest.menu.di

import org.koin.dsl.module

val menuModule = module {
    includes(viewModelModule, dataModule, networkModule)
}