package com.example.search

import com.necdetzr.common.base.BaseEvent

sealed interface SearchEvent : BaseEvent {
    data class OnSearchQuery(val query:String) : SearchEvent
}