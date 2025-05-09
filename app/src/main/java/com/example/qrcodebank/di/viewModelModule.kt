package com.example.qrcodebank.di

import com.example.qrcodebank.ui.camera.viewModel.CameraViewModel
import com.example.qrcodebank.ui.main.MainViewModel
import org.koin.dsl.module

val viewModules = module {
    factory { CameraViewModel(get()) }
    factory { MainViewModel() }
}