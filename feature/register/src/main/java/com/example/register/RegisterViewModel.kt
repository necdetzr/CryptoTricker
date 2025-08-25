package com.example.register

import androidx.lifecycle.viewModelScope
import com.example.network.manager.FirebaseAuthManager
import com.necdetzr.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager
) : BaseViewModel<RegisterViewState>(){

    override fun createInitialState(): RegisterViewState = RegisterViewState()
    override fun showLoading(isLoading: Boolean) = setState {copy(loading = isLoading)}

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.OnSignInEvent -> signIn(event.email,event.name,event.surname,event.password)

        }
    }

    fun signIn(email:String,name:String,surname:String,password:String){
        viewModelScope.launch {
            authManager.register(name,surname,email,password)
        }

    }



}