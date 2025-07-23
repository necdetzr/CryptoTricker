package com.example.settings.ui

import com.example.settings.data.SettingsItem
import com.necdetzr.common.base.IViewState


data class SettingsViewState(
    val settingsList: List<SettingsItem> = emptyList(),
    val expanded:Boolean = false,
    val showDialog:Boolean = false,
    val darkModeChecked:Boolean = false,
    override val loading:Boolean = false,
    override val showErrorModal: Boolean = false,
) : IViewState