package com.necdetzr.loodoscrypto

import CrashlyticsTree
import android.app.Application

import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber

@HiltAndroidApp
class CryptoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.plant(CrashlyticsTree())

    }
}