package com.necdetzr.settings.general.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.settings(navigateLogin:()->Unit,navigateBack:()->Unit,navigateLanguage:()->Unit){
    composable("settings"){
        SettingsScreen(
            onNavigateToLogin = navigateLogin,
            onNavigateToBack = navigateBack,
            onNavigateToLanguage = navigateLanguage
        )
    }
}