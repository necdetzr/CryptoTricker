package com.necdetzr.settings.profile.ui

import com.necdetzr.common.base.BaseEvent

sealed interface ProfileSettingsEvent : BaseEvent {

    data class ChangeName(val newName:String) : ProfileSettingsEvent

    data class SetNewName(val newName:String?) : ProfileSettingsEvent


}