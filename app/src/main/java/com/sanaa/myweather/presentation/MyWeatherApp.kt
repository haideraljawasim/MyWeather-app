package com.sanaa.myweather.presentation

import android.app.Application
import com.sanaa.myweather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyWeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyWeatherApp)
            modules(appModule)
        }
    }
}