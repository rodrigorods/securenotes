package com.rodrigorods.securenotes

import android.app.Application
import com.rodrigorods.injector.notes.dataModule
import com.rodrigorods.injector.notes.domainModule
import com.rodrigorods.injector.notes.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SecureNotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SecureNotesApplication)
            modules(uiModule, domainModule, dataModule)
        }
    }
}