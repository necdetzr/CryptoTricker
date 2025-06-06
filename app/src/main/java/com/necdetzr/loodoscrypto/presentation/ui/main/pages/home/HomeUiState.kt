package com.necdetzr.loodoscrypto.presentation.ui.main.pages.home

import com.necdetzr.loodoscrypto.domain.model.Coin

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val coins: List<Coin> = emptyList()
)
