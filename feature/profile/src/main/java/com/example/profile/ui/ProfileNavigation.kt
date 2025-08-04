package com.example.profile.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.profile(onNavigateToSettings:()->Unit){
    composable("profile"){
        ProfileScreen(
            onNavigateToSettings = onNavigateToSettings
        )
    }
}