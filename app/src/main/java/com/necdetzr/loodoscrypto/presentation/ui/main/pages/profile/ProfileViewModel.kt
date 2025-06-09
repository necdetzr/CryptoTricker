package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import androidx.lifecycle.ViewModel
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager
): ViewModel() {
    private val _userName = MutableStateFlow<String?>(null)
    val userName : StateFlow<String?> = _userName
    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail : StateFlow<String?> = _userEmail


    init{
        loadUserProfile()
    }

    private fun loadUserProfile(){
        val user = authManager.getCurrentUser()
        _userName.value = user?.displayName
        _userEmail.value = user?.email


    }
    fun logOut(){
        authManager.signOut()
    }
}