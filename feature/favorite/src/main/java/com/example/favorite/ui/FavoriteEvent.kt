package com.example.favorite.ui

import com.necdetzr.common.base.BaseEvent

sealed interface FavoriteEvent : BaseEvent{
    data class OnLoadFavorites(val userId:String) : FavoriteEvent
}