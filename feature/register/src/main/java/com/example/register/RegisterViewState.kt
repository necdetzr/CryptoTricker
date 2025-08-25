package com.example.register

import com.necdetzr.common.base.IViewState


data class RegisterViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val name:String = "",
    val surname:String = "",
    val email:String = "",
    val password:String = ""
) : IViewState