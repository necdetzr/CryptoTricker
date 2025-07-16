package com.example.settings.ui

import android.content.Context
import com.necdetzr.common.base.BaseEvent

sealed interface SettingsEvent : BaseEvent {
    data object SetDarkMode : SettingsEvent

    data class ExpandLanguage(val isExpanded:Boolean) : SettingsEvent

    data class ShowDialog(val isDialogShowed:Boolean) : SettingsEvent

    data class ChangeLanguage(val language:String, val context : Context) : SettingsEvent
}