package com.example.login

import androidx.lifecycle.viewModelScope
import com.example.network.manager.FirebaseAuthManager
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.datastore.model.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val dataStoreManager: DataStoreManager
) : BaseViewModel<LoginViewState>(){
    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnSignInEvent -> signIn(event.email,event.password)

            is LoginEvent.OnSetRememberMe -> setRememberMe()

            is LoginEvent.OnUpdateRememberMe -> updateRememberMe(event.isRemember)
        }

    }

    fun signIn(email:String,password:String){
        viewModelScope.launch {
            val loginState = authManager.login(email,password)
            setState { copy(loginState = loginState)}
        }
    }

    fun setRememberMe(){
        viewModelScope.launch {
            dataStoreManager.setRemember(uiState.value.isRemember)
        }
    }

    fun updateRememberMe(isRemember:Boolean){
        setState { copy(isRemember = isRemember)}
    }

}