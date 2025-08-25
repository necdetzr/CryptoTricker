package com.example.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MarketScreen(
    viewModel: MarketViewModel = hiltViewModel(),
    onNavigateToCoin:(String)->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    MarketPage(
        onNavigateToCoin = onNavigateToCoin,
        isLoading = uiState.loading,
        isError = uiState.showErrorModal,
        topCoins = uiState.topCoins,
        topLosers = uiState.topLosers,
        topGainers = uiState.topGainers
    )
}