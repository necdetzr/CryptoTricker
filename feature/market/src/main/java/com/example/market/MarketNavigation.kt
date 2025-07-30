package com.example.market

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.market(
    onNavigateToCoin:(String)->Unit
) {
    composable("market"){
        MarketScreen(
            onNavigateToCoin = onNavigateToCoin
        )
    }

}