package com.example.qrcodebank.di

import com.example.qrcodebank.data.camera.CameraRepositoryImpl
import com.example.qrcodebank.domain.camera.CameraRepository
import com.example.qrcodebank.domain.camera.CameraUseCase
import com.example.qrcodebank.domain.camera.CameraUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val interactorModule = module {
    factory<CameraUseCase> { CameraUseCaseImpl(get()) }
    factory <CameraRepository>{CameraRepositoryImpl(androidContext()) }
}