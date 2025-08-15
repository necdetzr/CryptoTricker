package com.example.favorite.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onNavigateToBack:()->Unit,
    onNavigateToCoin:(String)->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    FavoritePage(
        onNavigateToBack = onNavigateToBack,
        coins = uiState.favoriteCoins,
        onNavigateToCoin = onNavigateToCoin,


    )

}