package com.example.market

import com.necdetzr.common.base.IViewState
import com.necdetzr.home.component.domain.data.Coin

data class MarketViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val topCoins:List<Coin> = emptyList(),
    val topGainers:List<Coin> = emptyList(),
    val topLosers: List<Coin> = emptyList()


): IViewState