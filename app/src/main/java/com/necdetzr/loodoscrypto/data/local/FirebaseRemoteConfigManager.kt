package com.necdetzr.loodoscrypto.data.local

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.necdetzr.loodoscrypto.R
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteConfigManager @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) {
    suspend fun initializeRemoteConfig() {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings).await()
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults).await()
    }

    suspend fun fetchAndActivate(): Boolean {
        return try {
            firebaseRemoteConfig.fetchAndActivate().await()
        } catch (e: Exception) {
            Timber.e(e,"Failed to fetch remote config")
            false
        }
    }

    fun getString(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }
}