package com.example.login

import com.necdetzr.common.base.BaseEvent

sealed interface LoginEvent : BaseEvent{
    data class OnSignInEvent(val email:String,val password:String) : LoginEvent

    data object OnSetRememberMe: LoginEvent

    data class OnUpdateRememberMe(val isRemember:Boolean) : LoginEvent

}