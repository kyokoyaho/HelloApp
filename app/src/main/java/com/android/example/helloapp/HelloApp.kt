package com.android.example.helloapp

import android.app.Application
import android.os.Bundle
import timber.log.Timber

class HelloApp: Application() {
    override fun onCreate() {
        super.onCreate()

        //  initialize the Timber library
        Timber.plant(Timber.DebugTree())
    }
}