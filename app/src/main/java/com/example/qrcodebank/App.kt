package com.example.qrcodebank

import android.app.Application
import com.example.qrcodebank.di.interactorModule
import com.example.qrcodebank.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@App)
            modules(viewModules, interactorModule)
        }
        super.onCreate()
    }
}