package com.example.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.search(
    onNavigateToCoin:(String)->Unit
){
    composable("search") {
        SearchScreen(
            onNavigateToCoin = onNavigateToCoin
        )
    }
}