package com.android.cryptocoin.model.bitcoin.di

import com.android.shoppingcart.repository.ExchangeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModel
//    viewModel { MyViewModel(get()) }

    // Repository
    single { ExchangeRepository() }
}