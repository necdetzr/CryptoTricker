package com.example.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.detail(
    onNavigateToBack: ()->Unit,
){
    composable("detail/{coinId}") {
        DetailScreen(
            onNavigateToBack = onNavigateToBack
        )

    }
}