package com.necdetzr.settings.general.ui

import com.necdetzr.common.base.BaseEvent

sealed interface SettingsEvent : BaseEvent {
    data object SetDarkMode : SettingsEvent


    data class ShowDialog(val isDialogShowed:Boolean) : SettingsEvent

}