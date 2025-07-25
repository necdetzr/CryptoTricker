package com.example.settings.ui

import android.content.Context
import com.necdetzr.common.base.BaseEvent

sealed interface SettingsEvent : BaseEvent {
    data object SetDarkMode : SettingsEvent


    data class ShowDialog(val isDialogShowed:Boolean) : SettingsEvent

}