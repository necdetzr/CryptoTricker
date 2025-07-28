package com.example.profile.ui

import com.necdetzr.common.base.IViewState

data class ProfileViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val name:String? = "Guest User",
    val surname:String? = "Guest User",
    val email:String? = "Guest Email"

) : IViewState