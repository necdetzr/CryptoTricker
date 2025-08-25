package com.example.register

import com.necdetzr.common.base.BaseEvent

interface RegisterEvent : BaseEvent {
    data class OnSignInEvent(val name:String,val surname:String,val email:String,val password:String) : RegisterEvent
}