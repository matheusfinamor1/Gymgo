package com.example.gymgoproject

import android.app.Application
import com.example.gymgoproject.di.appModule
import com.example.gymgoproject.di.firebaseModule
import com.example.gymgoproject.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                appModule,
                storageModule,
                firebaseModule
            )
        }
    }
}