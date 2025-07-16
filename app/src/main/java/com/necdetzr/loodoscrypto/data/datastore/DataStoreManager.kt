package com.necdetzr.loodoscrypto.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.necdetzr.loodoscrypto.data.datastore.PreferenceKeys.LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import com.necdetzr.loodoscrypto.data.datastore.PreferenceKeys.DARK_MODE
import com.necdetzr.loodoscrypto.data.datastore.PreferenceKeys.REMEMBER
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

/*
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context){
    val languageFlow: Flow<String?> = context.dataStore.data
        .map { preferences->preferences[LANGUAGE] }

    val rememberMe : Flow<Boolean> = context.dataStore.data
        .map { it[REMEMBER] ?: false}
    val darkMode:Flow<Boolean> = context.dataStore.data
        .map { it[DARK_MODE] ?: false}

    suspend fun setRemember(remember:Boolean){
        Timber.d("Remember setted as $remember")

        context.dataStore.edit { preferences->
            preferences[REMEMBER] = remember
        }
    }




}

*/