package com.example.settings.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable




fun NavGraphBuilder.settings(navigateLogin:()->Unit,navigateBack:()->Unit){
    composable("settings"){
        SettingsScreen(
            onNavigateToLogin = navigateLogin,
            onNavigateToBack = navigateBack
        )
    }
}