package com.necdetzr.loodoscrypto.presentation.ui.main.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.local.FirebaseRemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RemoteConfigViewModel @Inject constructor(
    private val remoteConfigManager: FirebaseRemoteConfigManager
) : ViewModel() {
    val adviceText = mutableStateOf("Advice Text")

    init {
        viewModelScope.launch {
            remoteConfigManager.initializeRemoteConfig()
            fetchConfig()
        }
    }

    private fun fetchConfig() {
        viewModelScope.launch {
            val success = remoteConfigManager.fetchAndActivate()
            if (success) {
                adviceText.value = remoteConfigManager.getString("advice_test")
                Timber.d(adviceText.value)
            }
        }
    }
}