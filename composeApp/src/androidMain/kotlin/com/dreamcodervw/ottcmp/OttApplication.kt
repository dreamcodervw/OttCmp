package com.dreamcodervw.ottcmp

import android.app.Application
import com.dreamcodervw.ottcmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class OttApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@OttApplication)
        }
    }
}
