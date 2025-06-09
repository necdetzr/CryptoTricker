package com.necdetzr.loodoscrypto.presentation.ui.main.pages.market

import com.necdetzr.loodoscrypto.domain.model.Coin

data class MarketUiState(
    val isTopGainersLoading: Boolean = false,
    val isTopLosersLoading: Boolean = false,
    val isTopCoinsLoading: Boolean = false,
    val isError: Boolean = false
)
