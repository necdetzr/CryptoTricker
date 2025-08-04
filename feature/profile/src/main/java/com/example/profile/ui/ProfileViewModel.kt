package com.example.profile.ui

import com.example.network.manager.FirebaseAuthManager
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAuthManager: FirebaseAuthManager


) : BaseViewModel<ProfileViewState>(){
    override fun createInitialState(): ProfileViewState = ProfileViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }
    init {
        loadProfile()
    }


    fun loadProfile(){
        val user = firebaseAuthManager.getCurrentUser()
        val email = user?.email
        val name = user?.displayName

        setState { copy(name = name, surname = name, email = email) }


    }

    fun onEvent(event: ProfileEvent){
        when(event){
            is ProfileEvent.GetUser -> loadProfile()
        }

    }
}