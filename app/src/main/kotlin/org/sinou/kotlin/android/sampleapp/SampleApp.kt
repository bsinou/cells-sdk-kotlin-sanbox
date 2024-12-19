package org.sinou.kotlin.android.sampleapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SampleApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {// Launch dependency injection framework
            androidLogger(Level.INFO)
            androidContext(this@SampleApp)
            modules(allModules)
        }
    }
}