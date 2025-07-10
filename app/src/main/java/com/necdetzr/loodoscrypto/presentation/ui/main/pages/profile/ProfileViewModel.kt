package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.data.local.FirebaseRemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val dataStore: DataStoreManager,
    private val remoteConfigManager: FirebaseRemoteConfigManager
): ViewModel() {


    private val _uiState = MutableStateFlow(UserProfileUIState())
    val uiState:StateFlow<UserProfileUIState> = _uiState

    init{
        loadUserProfile()
        viewModelScope.launch {

            remoteConfigManager.initializeRemoteConfig()
            fetchConfig()
}
        viewModelScope.launch {
            dataStore.darkMode.collect { dark ->
                _uiState.update { it.copy(darkMode = dark) }
            }
        }

    }
    private suspend fun fetchConfig(){



            val localValue = remoteConfigManager.getString("advice_test")
            if (localValue.isNotBlank()) {
                _uiState.update { it.copy(adviceTest = localValue) }
            }
            val newValue = remoteConfigManager.fetchAndActivate()
            if (newValue) {
                _uiState.update { it.copy(adviceTest = remoteConfigManager.getString("advice_test")) }
            }


    }


    private fun loadUserProfile(){
        val user = authManager.getCurrentUser()
        val userName = user?.displayName ?: "Guest User"
        val email = user?.email?: "Guest Email"

        _uiState.update { it.copy(userName = userName,email = email) }


    }
    fun switchDarkMode(){
        val newDarkModeValue = !_uiState.value.darkMode
        _uiState.update { it.copy(darkMode = newDarkModeValue) }
        viewModelScope.launch {
            dataStore.setDarkMode(newDarkModeValue)

        }
    }
    fun logOut(
        callback:()-> Unit
    ){
        authManager.signOut()
        viewModelScope.launch {
            dataStore.setRemember(false)
            Timber.d("LOGOUT VIEWMODEL ${dataStore.rememberMe.first()}")
            callback.invoke()
        }
        //callback ekle

    }
}