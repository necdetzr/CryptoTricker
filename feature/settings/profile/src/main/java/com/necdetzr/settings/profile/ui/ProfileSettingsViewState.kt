package com.necdetzr.settings.profile.ui

import com.necdetzr.common.base.IViewState

data class ProfileSettingsViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val name : String? = null,
    val email:String? = null,
    val newName:String? = null,
    val count:Int = 0
) : IViewState