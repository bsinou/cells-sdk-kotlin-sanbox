package org.sinou.kotlin.android.sampleapp

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val allModules = module {
    single { NodeService(androidContext().applicationContext) }
}

