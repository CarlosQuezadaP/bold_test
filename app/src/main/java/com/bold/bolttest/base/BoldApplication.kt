package com.bold.bolttest.base

import android.app.Application
import com.bold.main_weather.di.apiModule
import com.bold.main_weather.di.repoModule
import com.bold.main_weather.di.viewModelModule
import com.bold.network.di.clientModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BoldApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BoldApplication)
            modules(clientModule, apiModule, repoModule, viewModelModule)
        }
    }
}