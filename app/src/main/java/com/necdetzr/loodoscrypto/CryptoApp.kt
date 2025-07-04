package com.necdetzr.loodoscrypto

import android.app.Application
import com.google.firebase.BuildConfig
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.utils.CrashlyticsTree
import com.necdetzr.loodoscrypto.utils.OptionsFunctions
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@HiltAndroidApp
class CryptoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(CrashlyticsTree())
        }
    }
}