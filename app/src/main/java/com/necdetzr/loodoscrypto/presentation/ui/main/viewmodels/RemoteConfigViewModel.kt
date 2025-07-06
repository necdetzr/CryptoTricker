package com.necdetzr.loodoscrypto.presentation.ui.main.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.local.FirebaseRemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RemoteConfigViewModel @Inject constructor(
    private val remoteConfigManager: FirebaseRemoteConfigManager
) : ViewModel() {
    private val _adviceText=MutableStateFlow("Advice Test")
    val adviceText : StateFlow<String> = _adviceText

    init {
        fetchConfig()
    }

    private fun fetchConfig() {
        viewModelScope.launch {
            println("AMK")
            remoteConfigManager.initializeRemoteConfig()
            val success = remoteConfigManager.fetchAndActivate()
            if (success) {
                val advice = remoteConfigManager.getString("advice_test")
                _adviceText.value = advice
                Timber.d("Fetched advice: $advice")

            } else {
                Timber.e("Remote config fetch failed.")

            }
        }
    }
}