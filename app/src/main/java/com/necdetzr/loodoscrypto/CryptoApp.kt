package com.necdetzr.loodoscrypto

import android.app.Application
import com.example.analytics.AnalyticsHelper
import com.example.analytics.AnalyticsManager

import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber

@HiltAndroidApp
class CryptoApp : Application() {
    lateinit var analyticsHelper: AnalyticsHelper
        private set
    override fun onCreate() {
        super.onCreate()
        analyticsHelper = AnalyticsManager(applicationContext)
        Timber.plant(Timber.DebugTree())

    }

}