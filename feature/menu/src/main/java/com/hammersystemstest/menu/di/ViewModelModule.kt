package com.hammersystemstest.menu.di

import com.hammersystemstest.menu.data.MenuRepositoryImpl
import com.hammersystemstest.menu.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel {
        MainViewModel(
            menuRepository = get<MenuRepositoryImpl>()
        )
    }
}