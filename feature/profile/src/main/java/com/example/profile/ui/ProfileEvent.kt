package com.example.profile.ui

import com.necdetzr.common.base.BaseEvent

sealed interface ProfileEvent : BaseEvent{
    data object GetUser : ProfileEvent
}