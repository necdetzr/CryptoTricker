package com.necdetzr.loodoscrypto.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.necdetzr.loodoscrypto.data.datastore.PreferenceKeys.LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context){
    suspend fun setLanguage(code:String){
        context.dataStore.edit{preferences->
            preferences[LANGUAGE] = code

        }
    }

    val languageFlow: Flow<String?> = context.dataStore.data
        .map { preferences->preferences[LANGUAGE] }

}