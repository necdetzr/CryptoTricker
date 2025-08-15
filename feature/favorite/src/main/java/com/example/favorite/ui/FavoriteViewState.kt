package com.example.favorite.ui

import com.necdetzr.common.base.IViewState
import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.data.DetailCoin

data class FavoriteViewState (
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val favoriteCoins:List<Coin> = emptyList(),
    val userId:String? = ""
) : IViewState