package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail

import com.necdetzr.loodoscrypto.domain.model.DetailCoin

data class CoinDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val userId:String? = "",
    val coin: DetailCoin? = null

)