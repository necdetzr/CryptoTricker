package com.example.login

import com.necdetzr.common.base.IViewState

data class LoginViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val email:String = "",
    val password:String = "",
    val loginState: Result<Unit>? = null,
    val isRemember:Boolean = false
): IViewState