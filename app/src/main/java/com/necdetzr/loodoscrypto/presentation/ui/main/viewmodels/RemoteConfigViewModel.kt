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
    private val _adviceTest = MutableStateFlow("Loading")
    val adviceTest : StateFlow<String> = _adviceTest

    init {
        viewModelScope.launch {
            remoteConfigManager.initializeRemoteConfig()
            fetchConfig()
        }
    }

    private fun fetchConfig() {
        viewModelScope.launch {

            val localValue = remoteConfigManager.getString("advice_test")
            if(localValue.isNotBlank()){
                _adviceTest.value = localValue
                Timber.d("Cached value = $localValue")
            }

            val success = remoteConfigManager.fetchAndActivate()
            if (success) {
                _adviceTest.value = remoteConfigManager.getString("advice_test")
                Timber.d("New remote Config Data: ${_adviceTest.value}")
            }
        }
    }
}