package com.necdetzr.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.home(
    onNavigateToSearch:()->Unit,
    onNavigateToMarket:()->Unit,
    onNavigateToFavorite:()->Unit,
    onNavigateToCoin:(String)->Unit
){
    composable("home"){
        HomeScreen(
            onNavigateToSearch = onNavigateToSearch,
            onNavigateToFavorite = onNavigateToFavorite,
            onNavigateToCoin = onNavigateToCoin,
            onNavigateToMarket = onNavigateToMarket
        )
    }

}