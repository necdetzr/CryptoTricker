package com.example.favorite.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.favorite(
    onBackButton:()->Unit,
    onNavigateToCoin:(String)-> Unit
){
    composable("favorites") {
        FavoriteScreen(
            onNavigateToBack = onBackButton,
            onNavigateToCoin = onNavigateToCoin
        )
    }
}