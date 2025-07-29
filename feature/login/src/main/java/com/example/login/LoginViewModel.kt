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

            is LoginEvent.OnUpdateRememberMe -> updateRememberMe(event.isRemember)

            is LoginEvent.OnEmailChange -> onEmailChange(event.email)

            is LoginEvent.OnPasswordChange -> onPasswordChange(event.password)
        }

    }

    fun signIn(email:String,password:String){
        viewModelScope.launch {
            showLoading(true)

            val loginState = authManager.login(email,password)
            setState { if(loginState.isSuccess){
                copy(loginStatus = LoginStatus.Success)
            }else{
                copy(loginStatus = LoginStatus.Error("Login Failed"))
            }
            }
            showLoading(false)
        }
    }
    fun onEmailChange(email:String){
        setState { copy(email = email) }
    }
    fun onPasswordChange(password:String){
        setState { copy(password = password) }
    }



    fun updateRememberMe(isRemember:Boolean){
        setState { copy(isRemember = isRemember)}
        viewModelScope.launch {
            dataStoreManager.setRemember(uiState.value.isRemember)
        }
    }

}