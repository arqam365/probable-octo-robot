package com.revzion.cognivia

import android.app.Application
import com.google.firebase.FirebaseApp
import com.revzion.cognivia.core.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        initializeKoin {
            androidContext(this@MyApplication)
        }
    }
}