package com.necdetzr.settings.profile.ui

import com.example.network.manager.FirebaseAuthManager
import com.google.firebase.auth.UserProfileChangeRequest
import com.necdetzr.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProfileSettingsViewModel
@Inject constructor(
    private val firebaseAuthManager: FirebaseAuthManager

) : BaseViewModel<ProfileSettingsViewState>() {
    override fun createInitialState(): ProfileSettingsViewState = ProfileSettingsViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }
    val user = firebaseAuthManager.getCurrentUser()

    fun onEvent(event: ProfileSettingsEvent){
        when(event){
            is ProfileSettingsEvent.ChangeName -> changeName(event.newName)
            is ProfileSettingsEvent.SetNewName -> setNewName(event.newName)
        }
    }

    init {
        loadProfile()

    }

    private fun loadProfile() {
        val name = user?.displayName
        val email = user?.email

        setState {
            copy(
                email = email, name = name
            )
        }
    }
    private fun changeName(newName:String){
        if(newName.length<=20){
            setState { copy(newName = newName) }
            getCount(newName)
        }

    }
    private fun getCount(newName:String){
        setState { copy(count = newName.length) }
    }

    private fun setNewName(newName: String?){
        showLoading(true)
        try {
            user?.updateProfile(
                UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(newName)
                    .build()

            )

        }catch (e: Exception){
            Timber.e("Some error occurred while changing username: $e")
        }
        showLoading(false)
    }
}


