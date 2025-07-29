package com.example.login

import com.necdetzr.common.base.BaseEvent

sealed interface LoginEvent : BaseEvent{
    data class OnSignInEvent(val email:String,val password:String) : LoginEvent

    data class OnUpdateRememberMe(val isRemember:Boolean) : LoginEvent

    data class OnEmailChange(val email:String): LoginEvent

    data class OnPasswordChange(val password:String) : LoginEvent

}