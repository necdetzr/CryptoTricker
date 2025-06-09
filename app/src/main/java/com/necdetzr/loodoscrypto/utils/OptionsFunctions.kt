package com.necdetzr.loodoscrypto.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object OptionsFunctions {
    fun setLocale(context: Context,languageCode:String): Context{
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)

    }
    fun restartAppWithLocale(activity: Activity, languageCode: String) {
        setLocale(activity,languageCode)
        val intent = activity.intent

        activity.finish()
        activity.overridePendingTransition(0,0)
        activity.startActivity(intent)
        activity.overridePendingTransition(0,0)

    }
}