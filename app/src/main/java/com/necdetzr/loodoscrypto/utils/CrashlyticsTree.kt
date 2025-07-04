package com.necdetzr.loodoscrypto.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree(){
    private val crashlytics = FirebaseCrashlytics.getInstance()
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if(priority == Log.WARN || priority == Log.ERROR || priority == Log.DEBUG ){
            crashlytics.log("$tag: $message")
            t?.let { crashlytics.recordException(it) }
            }


    }
}