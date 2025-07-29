package com.necdetzr.home.ui

import com.necdetzr.common.base.IViewState
import com.necdetzr.home.component.domain.data.Coin

data class HomeViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val topCoins: List<Coin> = emptyList()
): IViewState