package com.necdetzr.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToCoin:(String)->Unit,
    onNavigateToMarket:()->Unit,
    onNavigateToSearch:()->Unit,
    onNavigateToFavorite:()->Unit,

){
    val uiState by viewModel.uiState.collectAsState()

    HomePage(
        onNavigateToCoin = onNavigateToCoin,
        onNavigateToMarket = onNavigateToMarket,
        onNavigateToSearch = onNavigateToSearch,
        onNavigateToFavorite = onNavigateToFavorite,
        isLoading = uiState.loading,
        isError = uiState.showErrorModal,
        coins = uiState.topCoins

    )

}