package com.necdetzr.datastore.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import com.necdetzr.datastore.model.PreferenceKeys.DARK_MODE
import com.necdetzr.datastore.model.PreferenceKeys.LANGUAGE
import com.necdetzr.datastore.model.PreferenceKeys.REMEMBER
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context){
    val languageFlow: Flow<String?> = context.dataStore.data
        .map { preferences->preferences[LANGUAGE] }

    val rememberMe : Flow<Boolean> = context.dataStore.data
        .map { it[REMEMBER] ?: false}
    val darkMode:Flow<Boolean> = context.dataStore.data
        .map { it[DARK_MODE] ?: false}
    suspend fun setLanguage(code:String){
        context.dataStore.edit{preferences->
            preferences[LANGUAGE] = code

        }
    }
    suspend fun setRemember(remember:Boolean){

        context.dataStore.edit { preferences->
            preferences[REMEMBER] = remember
        }
    }
    suspend fun setDarkMode(darkMode:Boolean){
        context.dataStore.edit { preferences->
            preferences[DARK_MODE] = darkMode
        }
    }



}

