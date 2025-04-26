package com.example.qrcodebank.di

import com.example.qrcodebank.ui.main.MainViewModel
import org.koin.dsl.module

val viewModel = module {
    factory { MainViewModel() }
}