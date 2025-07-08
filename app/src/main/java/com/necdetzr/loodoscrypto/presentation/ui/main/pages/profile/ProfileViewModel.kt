package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val dataStore: DataStoreManager
): ViewModel() {
    private val _userName = MutableStateFlow<String?>(null)
    val userName : StateFlow<String?> = _userName
    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail : StateFlow<String?> = _userEmail
    private val _darkMode = MutableStateFlow(false)
    val darkMode : StateFlow<Boolean> = _darkMode
    private val _uiState = MutableStateFlow(UserProfileUIState())
    val uiState:StateFlow<UserProfileUIState> = _uiState

    init{
        loadUserProfile()
        viewModelScope.launch {
        dataStore.darkMode.collect { dark ->
            _darkMode.value = dark
        }
        }
    }


    private fun loadUserProfile(){
        val user = authManager.getCurrentUser()
        val userName = user?.displayName ?: "Guest User"
        val email = user?.email?: "Guest Email"

        _uiState.update { it.copy(userName = userName,email = email) }


    }
    fun switchDarkMode(){
        _darkMode.update { !it }
        viewModelScope.launch {
            dataStore.setDarkMode(_darkMode.value)

        }
    }
    fun logOut(){
        authManager.signOut()
    }
}