package com.example.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.lifecycle.viewModelScope
import com.example.monitor.AppEvent
import com.example.monitor.AppStateMonitor
import com.example.network.manager.FirebaseAuthManager
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.common.model.ToastModel
import com.necdetzr.common.model.ToastType
import com.necdetzr.datastore.model.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val dataStoreManager: DataStoreManager,
    private val appStateMonitor: AppStateMonitor
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

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            showLoading(true)

            val result = authManager.login(email, password)

            if (!result.isSuccess) {
                appStateMonitor.emitEvent(
                    AppEvent.ShowMessage(
                        appEventType = AppEvent.AppEventType.TOAST,
                        toastMessage = ToastModel(
                            title = "Error",
                            description = "Some error occurred while logging",
                            toastType = ToastType.ERROR,
                            icon = Icons.Default.Error
                        ),
                        friendlyMessage = null
                    )
                )
            }

            setState {
                copy(
                    loading = false,
                    loginStatus = if (result.isSuccess) {
                        LoginStatus.Success
                    } else {
                        LoginStatus.Error("Login Failed")
                    }
                )
            }
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