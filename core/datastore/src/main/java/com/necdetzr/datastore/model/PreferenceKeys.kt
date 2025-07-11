package com.necdetzr.datastore.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val LANGUAGE = stringPreferencesKey("language")
    val REMEMBER = booleanPreferencesKey("remember")
    val DARK_MODE = booleanPreferencesKey("dark_mode")
}